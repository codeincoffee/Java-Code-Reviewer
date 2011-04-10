package com.codeincoffee.codereview.common;
/**
 * @author Andy Chen 2011-3-8
 */
public interface Reviewable {
	
	public Result review(Class<?> clazz, int limit);
	
}

