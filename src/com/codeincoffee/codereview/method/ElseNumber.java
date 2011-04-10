package com.codeincoffee.codereview.method;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.FileMethod;
import com.codeincoffee.codereview.common.Line;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.ResultModule;
import com.codeincoffee.codereview.common.Reviewable;
import com.codeincoffee.codereview.utils.FileMethodUtil;

/**
 * @author Andy Chen 2011-3-10
 */
public class ElseNumber extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		for (FileMethod fileMethod : FileMethodUtil.fileMethods(clazz)) {
			int elseNumber = elseNumber(fileMethod);
			Result result = getResult(fileMethod, elseNumber, limit);
			if (result.failed()) return result;
		}
		return Result.getSuccess(getName());
	}

	private int elseNumber(FileMethod fileMethod) {
		int result = 0;
		for (Line line : fileMethod.compiled()) 
			if (line.contains("else{") || line.contains("else "))
				result++;
		return result;
	}
	
	

}

