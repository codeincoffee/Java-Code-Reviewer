package com.codeincoffee.codereview.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Result {
	
	public static Result SUCCESS;
	public static Result FAIL;
	
	private String reviewName;
	private String message;

	static {
		SUCCESS = new Result();
		FAIL = new Result();
	}
	
	public static Result getSuccess(String reviewName) {
		SUCCESS.reviewName = reviewName;
		return SUCCESS;
	}
	

	public static Result getFail(String reviewName, ResultModule resultModule) {
		FAIL.reviewName = reviewName;
		FAIL.message = FAIL.buildMessage(resultModule);
		return FAIL;
	}
	
	private String buildMessage(ResultModule resultModule) {
		StringBuffer sb = new StringBuffer().append("Your result in ")
		.append(reviewName).append(" is: ").append(resultModule.toFailedString());
		return sb.toString();
	}

	public String reviewName() {
		return reviewName;
	}
	
	public String message() {
		return message == null ? "Congratulations" : message;
	}
	
	public boolean failed() {
		return this == FAIL ? true : false;
	}
	
	public void log() {
		boolean success = this == SUCCESS;
		if (success & !Config.SUCCESS_LOG_OPEN) return;
		if (!success & !Config.FAIL_LOG_OPEN) return;
		Logger logger = Logger.getLogger(reviewName);
		logger.log(Level.INFO, (this == SUCCESS ? "success" : "fail") + " in review : " + reviewName + "\nmessage: " + message());
	}

}
