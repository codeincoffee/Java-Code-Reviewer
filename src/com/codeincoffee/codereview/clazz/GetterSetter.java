package com.codeincoffee.codereview.clazz;

import java.util.List;

import com.codeincoffee.codereview.common.BaseReview;
import com.codeincoffee.codereview.common.FileMethod;
import com.codeincoffee.codereview.common.Reflector;
import com.codeincoffee.codereview.common.Result;
import com.codeincoffee.codereview.common.Reviewable;
import com.codeincoffee.codereview.utils.FileMethodUtil;

/**
 * @author Andy Chen 2011-3-15
 */
public class GetterSetter extends BaseReview implements Reviewable {

	public Result review(Class<?> clazz, int limit) {
		int result = 0;
		for (FileMethod fileMethod : FileMethodUtil.fileMethods(clazz))
			if (isGetterOrSetter(fileMethod, clazz))
				result++;
		System.out.println(result);
		return getResult(new Reflector(clazz), result, limit);
	}

	private boolean isGetterOrSetter(FileMethod fileMethod, Class<?> clazz) {
		List<String> fieldNames = new Reflector(clazz).getFieldNames();
		return fileMethod.startsWithSetterGetter() 
			&& fieldNames.contains(fileMethod.nameRemovedGetSet());
	}

}

