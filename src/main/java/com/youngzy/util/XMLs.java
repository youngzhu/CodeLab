package com.youngzy.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author youngzy
 * @since 2024-08-13
 */
public class XMLs {
    private XMLs() {}


    public static String extractBody(String xml) {
        if (StringUtils.isBlank(xml)) {
            return null;
        }

        Pattern pattern = Pattern.compile("<(\\w+:)?\\bbody\\b>(.*?)</?(\\w+:)?\\bbody\\b>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(xml);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Tag <body> not found.");
        }

        return matcher.group(2);
    }
}
