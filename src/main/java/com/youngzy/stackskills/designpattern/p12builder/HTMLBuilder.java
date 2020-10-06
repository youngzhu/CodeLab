package com.youngzy.stackskills.designpattern.p12builder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTMLBuilder {
    private static final String HEADER = "<html><body>";
    private static final String FOOTER = "</body></html>";

    private List<URL> urls = new ArrayList<>();

    public void addURL(URL url) {
        urls.add(url);
    }

    public String getHtml() {
        StringBuilder sb = new StringBuilder(HEADER);

        for (URL url : urls) {
            String body = "<li><a href=\"" + url.getPath()
                    + "\" target=\"blank\" a></li>";
            sb.append("<br>" + body);
        }

        sb.append(FOOTER);
        return sb.toString();
    }
}
