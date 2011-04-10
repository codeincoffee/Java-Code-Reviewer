package com.codeincoffee.codereview.clazz;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.Reflector;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.Reviewable;

/**
 * @author Andy Chen 2011-3-11
 */
public class PublicParameterBoolean extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		Reflector reflector = new Reflector(clazz);
		int parameterBooleanNumber = 0;
		for (Method method : reflector.getMethods()) 
			if (hasParameterBoolean(method))
				parameterBooleanNumber++;
		return getResult(reflector, parameterBooleanNumber, limit);
	}

	private boolean hasParameterBoolean(Method method) {
		if (method.getModifiers() != Method.DECLARED) return false;
		Type[] parameters = method.getGenericParameterTypes();
		for (Type type : parameters) 
			if (("boolean").equalsIgnoreCase(type.toString()))
				return true;
		return false;
	}

}

