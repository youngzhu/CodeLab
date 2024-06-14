package com.youngzy.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

public class ReadWriteEAMTest {

    @Test
    public void readProcessWrite() throws IOException {
        ReadWriteEAM.readProcessWrite("src/test/resources/input.txt",
                "output/output.txt",
                rw -> {
                    rw.linesStream().forEach(line -> {
                        System.out.println(line);
                    });
                });
    }

    /**
     * 实现以下功能：
     * 1. 读取 input.txt 的内容
     * ~~2. 在非空行前加固定前缀: "output$行号: "~~
     * 2. 在非空行前加固定前缀: "output: "
     * 3. 写入 output.txt 文件
     * <p>
     * 带行号的话，就上下文相关了
     * 函数式编程是无状态的
     *
     * @throws IOException
     */
    @Test
    public void readProcessWrite2() throws IOException {
        ReadWriteEAM.readProcessWrite("src/test/resources/input.txt",
                "output/output.txt",
                rw -> {
                    rw.notBlankLinesStream()
                            .map(line -> "output: " + line)
                            .forEach(line -> rw.write(line));
                    rw.write(new Date().toString());
                });
    }
}