package com.youngzy.groovy.book.gina2.ch09

// 奇怪的位置，必须这么写才能执行
@BaseScript(LoggingScript)
import groovy.transform.BaseScript

abstract class LoggingScript extends Script {
  def log = []
  void println(args) {
    log << args
    System.out.println args
  }
}

println 'hello'
println 3 * 5

assert log.join(' ') == 'hello 15'
