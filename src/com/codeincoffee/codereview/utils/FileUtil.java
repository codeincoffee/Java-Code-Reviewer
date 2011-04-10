package com.codeincoffee.codereview.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.codeincoffee.codereview.common.Config;
import com.codeincoffee.codereview.common.FileMethod;
import com.codeincoffee.codereview.common.Line;
import com.codeincoffee.codereview.common.MethodContent;
import com.codeincoffee.codereview.common.SourceFile;

/**
 * @author Andy Chen 2011-2-16
 */
public class FileUtil {
	
	public static final String DOT = ".";
	public static final String ESCAPE = "\\";

	public static List<String> readLines(File file) {
		try {
			return FileUtils.readLines(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public static Class<?> getClassByFile(File file) {
		String filePath = file.getPath();
		filePath = getClassNameByFilePath(filePath);
		try {
			return Class.forName(filePath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static File getFileByClass(Class<?> clazz) {
		String className = clazz.getName();
		className = Config.root + DOT + className;
		className = className.replaceAll(ESCAPE + DOT, ESCAPE + File.separator);
		className += DOT + "java";
		return new File(className);
	}
	

	private static String getClassNameByFilePath(String filePath) {
		filePath = filePath.substring(filePath.indexOf(File.separator) + 1); // remove root\
		filePath = filePath.substring(0, filePath.indexOf(DOT)); // remove .java
		filePath = filePath.replaceAll("\\\\", DOT);
		return filePath;
	}

	public static int findMethodEndLine(List<Line> lines, int methodBeginLine) {
		int leftBraceNumber = 0; 
		int rightBraceNumber = 0;
		for( int index = methodBeginLine; index < lines.size(); index++ ) {
			Line line = lines.get(index);
			if (line.leftBrace()) leftBraceNumber ++;
			if (line.rightBrace()) rightBraceNumber ++;
			if (leftBraceNumber == rightBraceNumber) return index + 1;
		}
		return -1;
	}

	public static int getMethodBegin(List<Line> lines, Method method) {
		for (int i = 1; i < lines.size()-1; i++) {
			Line line = lines.get(i-1);
			if (line.isBlank()) continue;
			if (line.isMethodBegin(method.getName()))
				return i;
		}
		return -1;
	}

	public static int getMethodEnd(List<Line> lines, Method method) {
		int begin = getMethodBegin(lines, method);
		return findMethodEndLine(lines, begin-1);
	}

	public static FileMethod getFileMethod(SourceFile file, Method method) {
		List<Line> lines = file.lines();
		int begin = getMethodBegin(lines, method);
		int end = getMethodEnd(lines, method);
		MethodContent body = getMethodBody(lines, begin, end);
		return new FileMethod(method, body);
	}

	private static MethodContent getMethodBody(List<Line> lines, int begin, int end) {
		List<Line> body = new ArrayList<Line>();
		for (int i = begin; i < end; i++)
			if (!lines.get(i).isBlank())
				body.add(lines.get(i));
		return new MethodContent(body);
	}

	public static FileMethod[] getFileMethods(File file, List<Method> methods) {
		SourceFile sourceFile = new SourceFile(file);
		FileMethod[] fileMethods = new FileMethod[methods.size()];
		for (int i = 0; i < methods.size(); i++)
			fileMethods[i] = getFileMethod(sourceFile, methods.get(i));
		return fileMethods;
	}
	
	private static URL locateFromClasspath(String resourceName) {
		URL url = null;
		// attempt to load from the context classpath
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader != null) 
			url = loader.getResource(resourceName);
		// attempt to load from the system classpath
		if (url == null) 
			url = ClassLoader.getSystemResource(resourceName);
		return url;
	}
	
	public static File getConfigFile(String path) {
		URL  url = locateFromClasspath(path);
		return new File(url.getPath().substring(1));
	}
	
}

