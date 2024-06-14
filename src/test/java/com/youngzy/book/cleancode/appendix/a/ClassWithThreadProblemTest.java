package com.youngzy.book.cleancode.appendix.a;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ClassWithThreadProblemTest {

	@Test
	public void twoThreadsShouldFailEventually() throws Exception {
		final ClassWithThreadProblem classWithThreadProblem = new ClassWithThreadProblem();
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				classWithThreadProblem.takeNextId();
			}
		};
		
		for (int i = 0; i < 100000; ++i) {
			int startId = classWithThreadProblem.nextId;
			int expectedId = startId + 2;
			
			Thread t1 = new Thread(runnable);
			Thread t2 = new Thread(runnable);
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			
			int endId = classWithThreadProblem.nextId;
			
			if (endId != expectedId) {
				System.out.println(endId + "--" + expectedId);
				return;
			}
		}
		
		fail("Should have exposed a threading issue but it did not.");
	}
}
