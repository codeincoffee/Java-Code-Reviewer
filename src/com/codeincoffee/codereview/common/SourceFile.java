package com.codeincoffee.codereview.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.codeincoffee.codereview.utils.FileUtil;

/**
 * @author Andy Chen 2011-3-31
 */
public class SourceFile implements Module {
	
	private String fileName;
	
	private List<Line> lines = new ArrayList<Line>();
	
	public SourceFile(File file) {
		fileName = file.getName();
		List<String> contents = FileUtil.readLines(file);
		for (int i = 0; i < contents.size(); i++)
			lines.add(new Line(i+1, contents.get(i)));
	}
	
	public SourceFile(Class<?> clazz) {
		this(FileUtil.getFileByClass(clazz));
	}
	
	public void append(Line line) {
		lines.add(line);
	}

	public List<Line> lines() {
		return lines;
	}

	public String name() {
		return "file";
	}

	public String value() {
		return fileName;
	}

	public int lineNumber() {
		int lineNumber = 0;
		for (Line line : lines) 
			if (!line.isBlank())
				lineNumber++;
		return lineNumber;
	}
	
}

