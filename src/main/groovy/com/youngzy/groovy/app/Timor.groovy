package com.youngzy.groovy.app

import com.youngzy.groovy.constant.Paths

/**
 * 处理中国特色的调休日
 * https://timor.tech/api/holiday/year/{year}
 * 将数据转为标准的JSON格式，为我所用
 *
 * @author youngzy
 * @since 2024-12-20
 */

def reader = new File(Paths.mainResources, 'timor/2025.json').newReader()
def writer = new File(Paths.output, 'timor.json').newWriter()

reader.transformLine(writer) {
    // 删除 "01-01":
    def result = it.replaceAll(/"\d\d-\d\d":/, '')
    // "holiday":{ 转为 "holidays":[
    result = result.replace('"holiday":{', '"holidays":[')
    // 倒数第二个 } 换成 ]
    result[0..result.size() - 3] + ']}'
//    result
}