package com.codeincoffee.codereview.line;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.FileMethod;
import com.codeincoffee.codereview.common.Line;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.ResultModule;
import com.codeincoffee.codereview.common.Reviewable;
import com.codeincoffee.codereview.utils.FileMethodUtil;


/**
 * @author Andy Chen 2011-3-1
 */
public class DotNumber extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		for (FileMethod fileMethod : FileMethodUtil.fileMethods(clazz)) {
			Result result = result(fileMethod, limit);
			if (result.failed()) return result;
		}
		return Result.getSuccess(getName());
	}
	
	private Result result(FileMethod fileMethod, int limit) {
		for (Line line : fileMethod.compiled()) {
			int dotNumber = line.count(".");
			if (dotNumber > limit)
				return getResult(new ResultModule(line, dotNumber, limit));
		}
		return Result.getSuccess(getName());
	} 
}

