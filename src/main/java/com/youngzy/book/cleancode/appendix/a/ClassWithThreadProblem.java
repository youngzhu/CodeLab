package com.youngzy.book.cleancode.appendix.a;

/**
 * 多线程问题
 *
 * @author by youngzy 
 * 			on Jul 27, 2019
 *
 * Package&FileName: 
 * 		com.youngzy.book.cleancode.appendix.a.ClassWithThreadProblem
 */
public class ClassWithThreadProblem {
	int nextId;
	
	public int takeNextId() {
		return nextId ++;
	}
}
