package edu.sjsu.cmpe.library.domain;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {
    private long isbn;
    private String title;
    private String publicationdate;
    private String language;
    private int pages;
    private String status="avilable";
    List<Author> authlist; 
    List<Review> reviewlist= new ArrayList<Review>() ;
    

     /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    /**
     * @return the publication date
     */
    @JsonProperty("publication-date")
	public String getPublicationdate() {
		return publicationdate;
	}

	/**
     * @param publication date
     *            publication date
     */
	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}
	/**
     * @return the language
     */
	public String getLanguage() {
		return language;
	}
	
	/**
     * @param language
     *            language
     */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
     * @return the number of pages
     */
	@JsonProperty("num-pages")
	public int getPages() {
		return pages;
	}
	
	/**
     * @param number of pages
     *            number of pages
     */
	public void setPages(int pages) {
		this.pages = pages;
	}
	/**
     * @return the status
     */
	public String getStatus() {
		return status;
	}
	
	/**
     * @param status
     *            status
     */

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the authlist
	 */
	@JsonProperty("authors")
	public List<Author> getAuthlist() {
		return authlist;
	}

	/**
	 * @param authlist the authlist to set
	 */
	
	public void setAuthlist(List<Author> authlist) {
		this.authlist = authlist;
	}

	/**
	 * @return the reviewlist
	 */
	@JsonProperty("reviews")
	public List<Review> getReviewlist() {
		return reviewlist;
	}

	/**
	 * @param reviewlist the reviewlist to set
	 */
	public void setReviewlist(List<Review> reviewlist) {
		this.reviewlist = reviewlist;
	}
}