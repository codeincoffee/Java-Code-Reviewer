package com.codeincoffee.codereview.line;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.Line;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.Reviewable;
import com.codeincoffee.codereview.common.SourceFile;

public class Length extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		SourceFile file = new SourceFile(clazz);
		for (Line line : file.lines()) {
			if (line.length() > limit)
				return getResult(file, line.length(), limit);
		}
		return Result.getSuccess(getName());
	}
	
}
