package com.youngzy.lpr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;

/**
 * 转换工具
 *
 * 将原始的信息转换成想要的信息格式
 */
public abstract class ConvertUtil {

    public static void convertRate() throws Exception {
        Object o = System.getProperties();

        String projectDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        // windows下File.separator为\，需要Matcher.quoteReplacement(File.separator)获取。
        separator = Matcher.quoteReplacement(separator);

        String path = projectDir + ".src.main.resources.lpr.";
        path = path.replaceAll("\\.", separator);

        System.out.println(path);

        BufferedReader br = new BufferedReader(new FileReader(path + "rate.html"));

        String tmp = br.readLine();

        int counter = 0;
        String date = "", rate;
        while (tmp != null) {
            tmp = tmp.trim();

            // 6行一组数据
            if (counter % 6 == 0) {
                counter = 0;
            }

            if (counter == 0) {
                date = tmp;
            }
            if (counter == 5) {
                int idx = tmp.lastIndexOf(">");
                rate = tmp.substring(idx + 1);

                System.out.println("{ \"" + date + "\", \"" + rate + "\" },");
            }

            tmp = br.readLine();
            counter ++;
        }
    }
}
