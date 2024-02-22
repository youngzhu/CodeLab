package com.youngzy.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.stream.Stream;


/**
 * EAM: Execute Around Method pattern
 * 环绕执行模式
 *
 * 统一处理资源的关闭
 *
 * @author youngzy
 * @since 2024-02-22
 */
public class ReadWriteEAM {
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public ReadWriteEAM(String inputFile, String outputFile) throws IOException {
        this.reader = new BufferedReader(new FileReader(inputFile));
        this.writer = new BufferedWriter(new FileWriter(outputFile));
    }

    public Stream<String> linesStream() {
        return reader.lines();
    }

    public Stream<String> notBlankLinesStream() {
        return reader.lines().filter(StringUtils::isNotBlank);
    }

    public void write(String message) {
        try {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void close() throws IOException {
        System.out.println("resource closing...");
        this.reader.close();
        this.writer.close();
    }

    public static void readProcessWrite(String inputFile,
                                        String outputFile,
                                        ReadWriteConsumer consumer)
            throws IOException {
        ReadWriteEAM fileWriterEAM = new ReadWriteEAM(inputFile, outputFile);
        try {
            consumer.accept(fileWriterEAM);
        } finally {
            fileWriterEAM.close();
        }
    }
}
