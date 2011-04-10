package com.codeincoffee.codereview.common;

/**
 * @author Andy Chen 2011-3-31
 */
public class Review {
	
	private Reviewable reviewable;

	private int limit;
	
	private boolean active;
	
	public void setReviewable(String reviewable) {
		try {
			Reviewable review = (Reviewable) Class.forName(reviewable).newInstance();
			this.reviewable = review;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isActive() {
		return active;
	}

	public boolean reviewFail(Class<?> clazz) {
		Result result = reviewable.review(clazz, limit);
		result.log();
		return result.failed();
	}

	public int limit() {
		return limit;
	}
	
}

