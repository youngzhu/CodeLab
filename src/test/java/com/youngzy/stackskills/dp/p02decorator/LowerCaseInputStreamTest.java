package com.youngzy.stackskills.dp.p02decorator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;

public class LowerCaseInputStreamTest {

    LowerCaseInputStream lowerCaseInputStream;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws IOException {
        String projectDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        // windows下File.separator为\，需要Matcher.quoteReplacement(File.separator)获取。
        separator = Matcher.quoteReplacement(separator);

        String path = projectDir + ".src.test.resources.stackskills.";
        path = path.replaceAll("\\.", separator);

        // 链式调用
        InputStream in = new LowerCaseInputStream(
                new BufferedInputStream(
                        new FileInputStream(path + "LowerCaseInputStream.txt")
                )
        );

//        int c;
//        while ((c = in.read()) != -1) {
//            System.out.print((char)c);
//        }

        byte[] read = new byte[in.available()];
        in.read(read);
        String result = new String(read);

        Assert.assertEquals("i know the decorator pattern therefore i rule",
                result);


        in.close();
    }
}