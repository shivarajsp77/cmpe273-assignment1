package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepository;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {
   
    private final BookRepositoryInterface bookRepository;

    
    public ReviewResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @POST
    @Timed(name = "create-review")
    public Response createreview(@PathParam("isbn") LongParam isbn,Review request) {
	
	 
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	
    	
    	List <Review> temp = book.getReviewlist();
    	temp.add(request);
    	   		     
    	
	String location = "/books/" + book.getIsbn() + "/reviews/";
    	
    	//int tempvalue = bookRepository.getReviewId(isbn, request);
	
	LinksDto bookResponse = new LinksDto();
	bookResponse.addLink(new LinkDto("view-review", location, "GET"));
	
	return Response.status(201).entity(bookResponse).build();
    }
    

    @GET
    @Path("/{ID}/")
    @Timed(name = "view-review")
    public Response viewReview(@PathParam("isbn") LongParam isbn,@PathParam("ID") int ID) {
	
    	Review review = bookRepository.getRevByISBNID(isbn.get(), ID);
    	
    	Book book = bookRepository.getBookByISBN(isbn.get());

	String location = "/books/" + book.getIsbn() + "/reviews/" + ID;
    	
    	//int tempvalue = bookRepository.getReviewId(isbn, request);
	
	ReviewDto bookResponse = new ReviewDto(review);
	bookResponse.addLink(new LinkDto("view-review", location, "GET"));
	
	return Response.status(200).entity(bookResponse).build();
    }
    @GET
	@Timed(name="view-all-eviews")
	
		 public Response viewallreview(@PathParam("isbn") LongParam isbn) {
			   	Book book = bookRepository.getBookByISBN(isbn.get());
		    	List<Review> review = book.getReviewlist();
		        String location = "/books/" + book.getIsbn() + "/authors/";
		    	ReviewsDto bookResponse = new ReviewsDto(review);
		    	
			return Response.status(200).entity(bookResponse).build();
		    }
	
    
   }
