package com.codeincoffee.codereview.common;

import com.codeincoffee.codereview.utils.FileUtil;

public class BaseReview {

	public String getName() {
		String className = getClass().getName();
		String[] paths = className.split(FileUtil.ESCAPE + FileUtil.DOT);
		return paths[paths.length-2] + FileUtil.DOT + paths[paths.length-1];
	}

	public Result getResult(Module module, int result, int limit) {
        ResultModule resultModule = new ResultModule(module, result, limit);
        if (resultModule.fail())
		    return Result.getFail(getName(), resultModule);
		return Result.getSuccess(getName());
	}

	
}
