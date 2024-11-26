package com.youngzy.groovy.book.gina2.ch09

class Resource {                                      //Define Resource class with inbuilt statistics
    private static alive = 0
    private static used = 0
    Resource() { alive++ }
    def use() { used++ }
    static stats() { "$alive alive, $used used" }
}

class ResourceMain {
    def res1 = new Resource()                         //Declare one normal resource
    @Lazy res2 = new Resource()                       //Declare one @Lazy resource
    @Lazy static res3 = { new Resource() }()          //Declare static resource
    // 无参的构造函数将被调用
    // 如果没有无参的构造函数，运行时会报错
    @Lazy(soft=true) volatile Resource res4           //Thread safe and compatible with garbage collection
}

new ResourceMain().with {
    assert Resource.stats() == '1 alive, 0 used'      //After ResourceMain creation just res1 is alive
    res2.use()
    assert Resource.stats() == '2 alive, 1 used'      //Using res2, res3, res4 creates instances lazily
    res3.use()
    assert Resource.stats() == '3 alive, 2 used'      //Using res2, res3, res4 creates instances lazily
    res4.use()
    assert Resource.stats() == '4 alive, 3 used'      //Using res2, res3, res4 creates instances lazily
    assert res4 instanceof Resource                   //Verify res4 class
    def expected = 'res4=java.lang.ref.SoftReference'
    assert it.dump().contains(expected)               //Verify soft reference used internally
}
