package com.codeincoffee.codereview.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

/**
 * @author Andy Chen 2011-4-1
 */
public class Reviews {

	private static Reviews instance;
	private static List<Review> reviews = new ArrayList<Review>();
	
	
	private Reviews() {
		init();
	}
	
	public static List<Review> getInstance() {
		if (instance == null)
			instance = new Reviews();
		return reviews;
	}
	
	public synchronized void init() {
		try {
			getDigester().parse(Config.getReviewers());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		}
	}

	private Digester getDigester() {
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.push(this);
		return addReviewer(digester);
	}

	private Digester addReviewer(Digester digester) {
		digester.addObjectCreate("reviews/review",
		"com.codeincoffee.codereview.common.Review");
		digester.addSetProperties("reviews/review");
		digester.addSetNext("reviews/review", "addReview");
		return digester;
	}

	public void addReview(Review review) {
		if (review.isActive())
			reviews.add(review);
	}

	
}

