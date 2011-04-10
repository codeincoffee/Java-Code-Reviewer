package com.codeincoffee.codereview.common;


import org.apache.commons.lang.StringUtils;

/**
 * @author Andy Chen 2011-3-8
 */
public class Line implements Comparable<Object>, Module{
	
	private int lineNumber;
	private String content;
	
	public Line(int lineNumber, String content) {
		this.lineNumber = lineNumber;
		this.content = content;
	}

	public int lineNumber() {
		return lineNumber;
	}
	
	public String toString() {
		return content;
	}
	
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Line)) return false;
		Line line = (Line)object;
		return lineNumber == line.lineNumber &&
			content.equals(line.content);
	}
	
	public boolean contentEquals(Object object) {
		if (object == null || !(object instanceof Line)) return false;
		Line line = (Line)object;
		return content.equals(line.content);
	}

	public boolean complied() {
		return !(isLineComment() || 
			isBlockCommentStart() ||
			isBlockCommentEnd());
	}

	public boolean isLineComment() {
		return !isBlank() && content.startsWith("//");
	}
	
	public boolean isBlockCommentStart() {
		return !isBlank() && content.startsWith("/*");
	}
	
	public boolean isBlockCommentEnd() {
		return !isBlank() && content.contains("*/");
	}
	
	public boolean isBlank() {
		return StringUtils.isBlank(content);
	}

	public boolean isSingleLineBlockComment() {
		return isBlockCommentStart() & isBlockCommentEnd();
	}

	private String compiled() {
		int commentIndex = content.indexOf("\\");
		return commentIndex == -1 ? content : content.substring(commentIndex);
	}
	
	private String code() {
		String source = compiled();
		source = source.replaceAll("[\"]{1}.*[\"]{1}", "");
		return source;
	}
	
	public boolean contains(String str) {
		String content = code();
		return content.contains(str);
	}
	
	public int count(String key) {
		String code = code();
		if (key == null) return 0;  
		if (key.length() == 0 || code.length() < key.length())  return 0;
		return (code.length() - code.replace(key, "").length()) / key.length(); 
	}

	public int indexOf(String string) {
		return content.indexOf(string);
	}

	public int compareTo(Object object) {
		if (object instanceof Line) {
			Integer lineNumber = new Integer(((Line)object).lineNumber);
			return new Integer(lineNumber).compareTo(lineNumber);
		}
		return 0;
	}

	public boolean isMethodBegin(String methodName) {
		String code = code();
		return code.contains(methodName) & code.endsWith("{");
	}

	public boolean leftBrace() {
		return code().contains("{");
	}
	
	public boolean rightBrace() {
		return code().contains("}");
	}

	public int length() {
		return content.length();
	}

	public String name() {
		return "Line";
	}

	public String value() {
		return lineNumber + " " + content ;
	}
	
}

