package com.youngzy.groovy.book.gina2.ch17

//@Grab('org.spockframework:spock-core:1.0-groovy-2.4')
//@Grab('org.spockframework:spock-core:2.4-M5-groovy-3.0')
import spock.lang.Specification

class GivenWhenThenSpec extends Specification {

  def "test adding a new item to a set"() {
    given:
    def items = [4, 6, 3, 2] as Set

    when:
    items << 1

    // 不需要assert了
    // then 中的都会校验是否为真
    then:
    items.size() == 5
  }
}
