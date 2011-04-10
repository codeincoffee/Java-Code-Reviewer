package com.codeincoffee.codereview.method;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.FileMethod;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.Reviewable;
import com.codeincoffee.codereview.utils.FileMethodUtil;


/**
 * @author Andy Chen 2011-3-1
 */
public class Length extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		for (FileMethod fileMethod : FileMethodUtil.fileMethods(clazz)) {
			int length = fileMethod.length();
			Result result = getResult(fileMethod, length, limit);
			if (result.failed()) return result;
		}
		return Result.getSuccess(getName());
	}
	
}

