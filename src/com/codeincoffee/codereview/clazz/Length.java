package com.codeincoffee.codereview.clazz;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.ResultModule;
import com.codeincoffee.codereview.common.Reviewable;
import com.codeincoffee.codereview.common.SourceFile;

/**
 * @author Andy Chen 2011-3-10
 */
public class Length extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		SourceFile file = new SourceFile(clazz);
		ResultModule resultModule = new ResultModule(file, file.lineNumber(), limit);
		return getResult(resultModule);
	}
	
}
