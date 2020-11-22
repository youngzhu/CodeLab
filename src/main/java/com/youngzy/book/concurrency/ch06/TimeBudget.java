package com.youngzy.book.concurrency.ch06;

import java.util.*;
import java.util.concurrent.*;

/**
 * 6-17 在预定时间内请求旅游报价
 */
public class TimeBudget {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo,
                                                   Set<TravelCompany> companySet,
                                                   Comparator<TravelQuote> ranking,
                                                   long time, TimeUnit unit)
        throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companySet)
            tasks.add(new QuoteTask(company, travelInfo));

        List<Future<TravelQuote>> futures = executorService.invokeAll(tasks, time, unit);

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIterator = tasks.iterator();
        for (Future<TravelQuote> future : futures) {
            QuoteTask task = taskIterator.next();
            try {
                quotes.add(future.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        Collections.sort(quotes, ranking);
        return quotes;
    }
}

class QuoteTask implements Callable<TravelQuote> {
    private final TravelCompany company;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }

    TravelQuote getFailureQuote(Throwable throwable) {
        return null;
    }

    TravelQuote getTimeoutQuote(CancellationException e) {
        return null;
    }

    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }
}

interface TravelCompany {
    TravelQuote solicitQuote(TravelInfo travelInfo) throws Exception;
}

interface TravelQuote {}
interface TravelInfo {}