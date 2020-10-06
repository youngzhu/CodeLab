package com.youngzy.stackskills.designpattern.p02decorator;

import com.youngzy.util.ResourceFileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class LowerCaseInputStreamTest {

    @Test
    public void test() throws IOException {
        String path = ResourceFileUtil.getTestResourcePath("stackskills");

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