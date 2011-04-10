package com.codeincoffee.codereview.clazz;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.Reflector;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.ResultModule;
import com.codeincoffee.codereview.common.Reviewable;

/**
 * @author Andy Chen 2011-3-14
 */
public class FieldNumber extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		Reflector reflector = new Reflector(clazz);
		return getResult(reflector, reflector.fieldSize(), limit);
	}

}

