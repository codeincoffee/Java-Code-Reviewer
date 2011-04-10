package com.codeincoffee.codereview.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import com.codeincoffee.codereview.common.FileMethod;
import com.codeincoffee.codereview.common.Reflector;

/**
 * @author Andy Chen 2011-3-9
 */
public class FileMethodUtil {
	
	public static FileMethod[] fileMethods(Class<?> clazz) {
		File file = FileUtil.getFileByClass(clazz);
		List<Method> methods = new Reflector(clazz).getMethods();
		FileMethod[] fileMethods = FileUtil.getFileMethods(file, methods);
		return fileMethods;
	}
	
}

