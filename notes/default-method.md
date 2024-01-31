# default method

本来以为可以实现类似“插件”的功能，经测试，不行。

方法的优先级：自己 > 父类 > 接口（default method）

详见：[src/test/java/com/youngzy/java/defaultmethod/DefaultMethodTest.java](src/test/java/com/youngzy/java/defaultmethod/DefaultMethodTest.java)

所谓的“插件”模式，只有在父类完全缺省该方法的情况下才有用，详见[查看](src/main/java/com/youngzy/java/defaultmethod/plugin)