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
	
    /**
     * @param root set the project root, default root is  "src"
     * @param successLopOPen whether print log in console in every success review, default false
     * @param failLogOpen whether print log in console if review fail, default true
     */
	public static CodeReview getInstance(String root, boolean successLogOpen, boolean failLogOpen) {
		Config.SUCCESS_LOG_OPEN = successLogOpen;
		Config.FAIL_LOG_OPEN = failLogOpen;
		Config.root = root;
		return getInstance();
	}
	
    /**
     * @param successLopOPen whether print log in console in every success review, default false
     * @param failLogOpen whether print log in console if review fail, default true
     */
	public static CodeReview getInstance(boolean successLogOpen, boolean failLogOpen) {
		Config.SUCCESS_LOG_OPEN = successLogOpen;
		Config.FAIL_LOG_OPEN = failLogOpen;
		return getInstance();
	}
	
    /**
     * @root the project root, default "src"
     * get a instance of CodeReview with project root setting
     */
	public static CodeReview getInstance(String root) {
		Config.root = root;
		return getInstance();
	}
	
    /**
     * get an instance of CodeReview if project root is "src"
     */
	public static CodeReview getInstance() {
		if (instance == null) {
			instance = new CodeReview();
			return instance;
		}
		return instance;
	}
    
    /**
     * @param clazz
     * the clazz which you want to review, quite useful if you are a TDDer
     */
    public static void review(Class<?> clazz) {
    	review(getReviewers(), clazz);
	}
    
	/** 
     * @param filePath 
     * the filePath shoud be project relative path
     * e.g. src/com/codeincoffee/codeview/CodeReview.java
     */
    public static void review(String filePath) {
		Class<?> clazz = FileUtil.getClassByFile(new File(filePath));
		review(getReviewers(), clazz);
	}
    
    /** 
     * @param file 
     * the file shoud be project relative path
     * e.g. new File(src/com/codeincoffee/codeview/CodeReview.java)
     */
    public static void review(File file) {
		Class<?> clazz = FileUtil.getClassByFile(file);
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

}

