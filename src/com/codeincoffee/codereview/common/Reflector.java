package com.codeincoffee.codereview.common;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codeincoffee.codereview.utils.FileUtil;

/**
 * @author Andy Chen 2010-12-13
 */
public class Reflector implements Module {
	
	private Class<?> clazz;
	private List<Field> fields;
	private List<Method> methods;
	private List<Constructor<?>> constructors;
	
	public Reflector(File file) {
		this(FileUtil.getClassByFile(file));
	}
	
	public Reflector(Class<?> clazz){
		this.clazz = clazz;
	}
	
	public List<Field> getFields(){
		if (fields == null)
			return Arrays.asList(clazz.getDeclaredFields());
		return fields;
	}
	
	public List<String> getFieldNames(){
		List<String> fieldNames = new ArrayList<String>();
		for (Field field : getFields())
			fieldNames.add(field.getName());
		return fieldNames;
	}
	
	public List<Method> getMethods(){
		if (methods == null)
			return Arrays.asList(clazz.getDeclaredMethods());
		return methods;
	}
	
	public List<Constructor<?>> getConstructors(){
		if (constructors == null)
			return Arrays.asList(clazz.getDeclaredConstructors());
		return constructors;
	}

	public String name() {
		return "class";
	}

	public String value() {
		return clazz.getName();
	}

	public int fieldSize() {
		return getFields().size();
	}
	
}

