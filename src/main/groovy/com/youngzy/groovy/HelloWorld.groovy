import groovy.io.FileType

import java.nio.file.Path
import java.nio.file.Paths

/*
来自 https://doc.yonyoucloud.com/doc/wiki/project/groovy-introduction
 */


def PROJECT_NAME = 'CodeLab'
def userDir = System.properties.getProperty('user.dir')
def index = userDir.indexOf(PROJECT_NAME)
def PROJECT_ROOT = userDir.substring(0, index) + PROJECT_NAME
def TEST_RESOURCES = PROJECT_ROOT + '/src/test/resources'

def baseDir = TEST_RESOURCES
def baseOutputDir = PROJECT_ROOT + '/output'


//def properties = System.properties
//properties.forEach { key, value ->
//    println "$key: $value"
//}

//def path = Paths.get(userDir)

// ARM（Automatic Resource Management，自动资源管理）
//new File(baseDir,'input.txt').eachLine('UTF-8') {
//    println it
//}
//new File(baseDir,'input.txt').eachLine {line, lineNo ->
//    println "Line $lineNo: $line"
//}

//new File(baseDir,'input.txt').withReader('UTF-8') { reader ->
//    reader.eachLine {
//        println it
//    }
//}
//def count = 0, MAXSIZE = 3
//new File(baseDir,"input.txt").withReader { reader ->
//    while (reader.readLine()) {
//        if (++count > MAXSIZE) {
//            throw new RuntimeException('should only have 3 lines')
//        }
//    }
//}

//你可能会需要将某个文本文件的行放入列表中，可以这样写：
//def list = new File(baseDir, 'input.txt').collect {it}
//println(list.size())
//
////你甚至还可以利用 as 操作符将获得的文件内容放到一个关于行的数组中：
//def array = new File(baseDir, 'input.txt') as String[]
//println(array.length)
//assert array.length == list.size()


// 写文件
//new File(baseOutputDir,'temp.txt').withWriter('utf-8') { writer ->
//    writer.writeLine 'Into the ancient pond'
//    writer.writeLine 'A frog jumps'
//    writer.writeLine 'Water’s sound!'
//}
// 追加，而不是覆盖
//new File(baseOutputDir,'temp.txt') << '''Into the ancient pond
//A frog jumps
//Water’s sound!'''

//////////////////////////////////////////////////////
///////////////      遍历文件目录       ////////////////
//////////////////////////////////////////////////////

def dir = new File(PROJECT_ROOT)

//dir.eachFile { file ->
//    println file.name
//}
// .开头的文件
//dir.eachFileMatch(~/\..*/) { file ->
//    println file.name
//}

// 包括路径和文件
//dir.eachFileRecurse { file ->
//    println file.name
//}

// 只有文件
//dir.eachFileRecurse(FileType.FILES) { file ->
//    println file.name
//}