package com.youngzy.groovy.constant

/**
 * 路径相关的常量
 *
 * @author youngzy
 * @since 2024-12-20
 */
class Paths {
    private Paths() {}

    /**
     * Groovy和Java执行的结果不一样
     * Java: 当前项目的根目录
     * Groovy: 当前执行文件的目录，在执行Groovy脚本时。单元测试没问题
     */
    private static final String ROOT = System.getProperty("user.dir");

    public static final String SOURCE = "src";
    public static final String MAIN_RESOURCES = "main/resources";
    public static final String TEST_RESOURCES = "test/resources";

    public static final String OUTPUT = "output"

    static String getRoot() {
        def root = ROOT
        // 执行Groovy脚本时，返回的是执行脚本的当前目录
        if (root.contains('src')) {
            def idx = root.indexOf('src')
            root = root[0..idx-1]
        }

        root
    }

    static String getMainResources() {
        java.nio.file.Paths.get(root, SOURCE, MAIN_RESOURCES)
    }

    static String getOutput() {
        java.nio.file.Paths.get(root, OUTPUT)
    }

}
