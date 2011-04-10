package com.codeincoffee.codereview;

import java.io.File;
import java.util.List;

import com.codeincoffee.codereview.common.Config;
import com.codeincoffee.codereview.common.Review;
import com.codeincoffee.codereview.common.Reviews;
import com.codeincoffee.codereview.utils.FileUtil;

/**
 * @author Andy Chen 2011-3-18
 */
public class CodeReview {
	
	private static CodeReview instance;
	
	private CodeReview() {}
	
	public static CodeReview getInstance(String root, boolean successLogOpen, boolean failLogOpen) {
		Config.SUCCESS_LOG_OPEN = successLogOpen;
		Config.FAIL_LOG_OPEN = failLogOpen;
		Config.root = root;
		return getInstance();
	}
	
	public static CodeReview getInstance(boolean successLogOpen, boolean failLogOpen) {
		Config.SUCCESS_LOG_OPEN = successLogOpen;
		Config.FAIL_LOG_OPEN = failLogOpen;
		return getInstance();
	}
	
	public static CodeReview getInstance(String root) {
		Config.root = root;
		return getInstance();
	}
	
	public static CodeReview getInstance() {
		if (instance == null) {
			instance = new CodeReview();
			return instance;
		}
		return instance;
	}
	
	public static void review(String filePath) {
		Class<?> clazz = FileUtil.getClassByFile(new File(filePath));
		review(getReviewers(), clazz);
	}

	public static void review(Class<?> clazz) {
		review(getReviewers(), clazz);
	}
	
	private static List<Review> getReviewers(){
		List<Review> reviewables = null;
		reviewables =  Reviews.getInstance();
		return reviewables;
	}
	
	private static void review(List<Review> reviewables, Class<?> clazz) {
		for (Review review : reviewables) {
			if (review.reviewFail(clazz)) 
				return;
		}
	}

	public static void review(File directory) {
		if (!directory.isDirectory())
			throw new IllegalArgumentException("this is not a directory");
	}

}

