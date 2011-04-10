package com.codeincoffee.codereview.common;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Andy Chen 2011-3-9
 */
public class MethodContent {
	
	private List<Line> source; 
	private List<Line> compiled; //after remove comments
	
	public MethodContent(List<Line> wholeBody) {
		this.source = wholeBody;
		removeComments();
	}

	public int length() {
		return source.size();
	}
	
	public void removeComments() {
		if (compiled != null) return;
		compiled = removeComments(source);
	}

	private List<Line> removeComments(List<Line> wholeBody) {
		List<Line> compiledBody = removeLineComments(wholeBody);
		compiledBody = removeBlockComments(compiledBody);
		return compiledBody;
	}

	private List<Line> removeLineComments(List<Line> wholeBody) {
		List<Line> result = new ArrayList<Line>();
		for (Line line : wholeBody)
			if (!line.isLineComment())
				result.add(line);
		return result;
	}

	public void removeLineComments() {
		compiled = removeLineComments(source);
	}

	public void removeBlockComments() {
		compiled = removeBlockComments(source);
	}

	private List<Line> removeBlockComments(List<Line> body) {
		List<Line> result = new ArrayList<Line>();
		boolean inBlock = false;
		for (Line line : body) {
			if (line.isSingleLineBlockComment()) continue;
			if (line.isBlockCommentStart()) inBlock = !inBlock;
			if (!inBlock) result.add(line);
			if (line.isBlockCommentEnd()) inBlock = !inBlock;
		}
		return result;	
	}

	public List<Line> compiled() {
		return compiled;
	}
	
	public String startLine() {
		return source.get(0).lineNumber() - 1 +"";
	}
	
}

