package edu.sjsu.cmpe.library.dto;

import java.util.List;

import edu.sjsu.cmpe.library.domain.Review;

public class ReviewsDto {

	List<Review> review;

	public ReviewsDto(List<Review> review){
		super();
		this.review = review;
	}
	/**
	 * @return the review
	 */
	public List<Review> getReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(List<Review> review) {
		this.review = review;
	}
	
	
}
