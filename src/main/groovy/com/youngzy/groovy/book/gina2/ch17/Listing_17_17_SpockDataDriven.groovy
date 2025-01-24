package com.youngzy.groovy.book.gina2.ch17

import spock.lang.*

class Listing_17_17_SpockDataDriven extends Specification {

  // 左边栏会把这些参数都填上
  // 一开始我看的是右边，发现跟之前提示没差别啊，原来是看的地方不对
  @Unroll
  def "Scenario #scenario: #tempFºF should convert to #tempCºC"() {
    expect:
    Converter.celsius(tempF) == tempC

    where:
    scenario                  | tempF || tempC
    'Freezing'                |    32 ||     0
    'Garden party conditions' |    68 ||    20
    'Beach conditions'        |    95 ||    35
    'Boiling'                 |   212 ||   101
  }

  // 如果有错误，提示不明确
  def "test temperature scenarios"() {
    expect:
    Converter.celsius(tempF) == tempC

    where:
    scenario                  | tempF || tempC
    'Freezing'                |    32 ||     0
    'Garden party conditions' |    68 ||    20
    'Beach conditions'        |    95 ||    35
    'Boiling'                 |   212 ||   100
  }

}
