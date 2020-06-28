package com.youngzy.util;

import java.util.regex.Matcher;

public final class ResourceFileUtil {
    private static final String PLACE_HOLDER = "$_$";

    public static String getMainResourcePath(String parent) {

        return getResourcePath("main", parent);
    }

    public static String getTestResourcePath(String parent) {

        return getResourcePath("test", parent);
    }

    private static String getResourcePath(String resourceType, String parent) {
        String sourcePath = getProjectSourcePath();
        String resourcePath = addSubDirectory(sourcePath, PLACE_HOLDER);
        resourcePath = addSubDirectory(resourcePath, "resources");
        String result = addSubDirectory(resourcePath, parent);

        String separator = System.getProperty("file.separator");
        // windows下File.separator为\，需要Matcher.quoteReplacement(File.separator)获取。
        separator = Matcher.quoteReplacement(separator);

        result = result.replaceAll("\\.", separator);

        return result.replace(PLACE_HOLDER, resourceType);
    }

    private static String getProjectSourcePath() {
        //        Object o = System.getProperties();

        String projectDir = System.getProperty("user.dir");

        return addSubDirectory(projectDir,"src");
    }

    private static String addSubDirectory(String parent, String sub) {
        if (! parent.endsWith(".")) {
            parent = parent.concat(".");
        }
        return parent.concat(sub).concat(".");
    }
}
