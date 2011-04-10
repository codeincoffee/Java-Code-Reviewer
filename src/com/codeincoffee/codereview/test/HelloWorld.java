package com.codeincoffee.codereview.test;

public class HelloWorld {
	
	public String say(String string) {
		return string;
	}

	public boolean methodTooLong() {
		String str = "Hello world".substring(1).substring(1).substring(1);
		System.out.println(str);
		return true;
	}


}
