package edu.sjsu.cmpe.library.repository;

import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;


 
public interface BookRepositoryInterface{
    
    Book saveBook(Book newBook);
       
    Book getBookByISBN(Long isbn);
    
    boolean delBookByISBN(Long isbn);
    
    boolean updateBookByISBN(Long isbn,String status);

	Author getAuthByISBNID(Long isbn,int id);

	Review getRevByISBNID(Long isbn, int ID);
	
}
