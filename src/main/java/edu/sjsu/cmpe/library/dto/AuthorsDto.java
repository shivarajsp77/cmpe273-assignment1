package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;

public class AuthorsDto {
	

    
	private List<Author> author;
    
    /**
	 * @return the author
	 */
	public List<Author> getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public AuthorsDto(List<Author> author){
		super();
		this.author = author;   
    
}
}    