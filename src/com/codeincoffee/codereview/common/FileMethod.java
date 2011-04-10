package com.codeincoffee.codereview.common;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;


/**
 * @author Andy Chen 2011-3-8
 */
public class FileMethod implements Module {
	
	private Method method;
	private MethodContent body;
	
	public FileMethod(Method method, MethodContent body) {
		this.method = method;
		this.body = body;
	}

	public int length() {
		return body.length();
	}

	public List<Line> compiled() {
		return body.compiled();
	}
	
	public int count(String str) {
		int number = 0;
		for (Line line : body.compiled())
			if (line.contains(str))
				number ++;
		return number;
	}

	public Type[] getGenericParameterTypes() {
		return method.getGenericParameterTypes();
	}
	
	public String nameRemovedGetSet() {
		String name = name().substring(3);
		String firstLetter = (name.charAt(0)+"").toLowerCase();
		return firstLetter + name.substring(1);
	}
	
	public boolean startsWithSetterGetter() {
		String name = name();
		return (name.startsWith("get") || name.startsWith("set"))
				&& (name.length() > 3) 
				&& Character.isUpperCase(name.charAt(3)); 
	}
	
	public String name() {
		return method.getName();
	}

	public String value() {
		return "line:" + body.startLine();
	}
}

