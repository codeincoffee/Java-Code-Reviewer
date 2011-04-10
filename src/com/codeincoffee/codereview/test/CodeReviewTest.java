package com.codeincoffee.codereview.test;

import com.codeincoffee.codereview.CodeReview;

import junit.framework.TestCase;

public class CodeReviewTest extends TestCase {
	
	private HelloWorld helloWorld;
	
	public void setUp() {
		helloWorld = new HelloWorld();
	}
	
	public void testCode() {
		CodeReview.review(helloWorld.getClass());
	}
	
	public void testHelloWorld() {
		String words = helloWorld.say("Hello world");
		assertEquals("Hello world", words);
	}
	
	public void testCreateMethodTooLong() {
		assertTrue(helloWorld.methodTooLong());
	}

}
