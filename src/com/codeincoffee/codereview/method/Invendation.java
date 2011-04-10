package com.codeincoffee.codereview.method;

import java.util.Set;
import java.util.TreeSet;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.FileMethod;
import com.codeincoffee.codereview.common.Line;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.Reviewable;
import com.codeincoffee.codereview.utils.FileMethodUtil;

/**
 * @author Andy Chen 2011-3-14
 */
public class Invendation extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		FileMethod[] fileMethods = FileMethodUtil.fileMethods(clazz);
		for (FileMethod fileMethod : fileMethods) {
			int indentationCount = indentationCount(fileMethod);
			Result result = getResult(fileMethod, indentationCount, limit);
			if (result.failed()) return result;
		}
		return Result.getSuccess(getName());
	}

	private int indentationCount(FileMethod fileMethod) {
		Set<Integer> set = new TreeSet<Integer>();
		for (Line line : fileMethod.compiled()) 
			if (line.contains("for ("))
				set.add(line.indexOf("for"));
		return set.size();
	}

}

