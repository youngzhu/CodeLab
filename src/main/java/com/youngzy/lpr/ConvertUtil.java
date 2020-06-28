package com.youngzy.lpr;

import com.youngzy.util.ResourceFileUtil;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 转换工具
 *
 * 将原始的信息转换成想要的信息格式
 */
abstract class ConvertUtil {

    public static void convertRate() throws Exception {
        String path = ResourceFileUtil.getMainResourcePath("lpr");
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
