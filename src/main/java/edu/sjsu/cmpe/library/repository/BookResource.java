package edu.sjsu.cmpe.library.repository;

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

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.repository.BookRepository;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
    	
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookDto bookResponse = new BookDto(book);
	
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),
		"GET"));
	bookResponse.addLink(new LinkDto("update-book",
		"/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(),
			"GET"));
	// add more links
	return bookResponse;
    }

    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	 
    	Book savedBook = bookRepository.saveBook(request);
	

	String location = "/books/" + savedBook.getIsbn();
	String location1= "/books/" + savedBook.getIsbn() + "/reviews";
	
	LinksDto bookResponse = new LinksDto();
	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	bookResponse.addLink(new LinkDto("update-book", location, "POST"));
	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", location1, "POST"));
	// Add other links if needed

	return Response.status(201).entity(bookResponse).build();
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name= "delete-book")
    public Response deleteBook(@PathParam("isbn") LongParam isbn) {
    
    	Boolean reply = bookRepository.delBookByISBN(isbn.get());
    	
    	String location = "/books";// + book.getIsbn();
    	
        if(reply==true){
    	
//    	Book savedBook = bookRepository.saveBook();
    	//BookDto bookResponse = new BookDto(book);
    	LinksDto bookResponse = new LinksDto();
    	bookResponse.addLink(new LinkDto("Create-book", location, "POST"));
    	  	
    return Response.status(200).entity(bookResponse).build();}
        else
        
        	return Response.status(406).build();
        	
          }
    
    
    @PUT
    @Path("/{isbn}")
    @Timed(name= "update-book")
    public Response updateBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) {
    
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	
    	Boolean reply = bookRepository.updateBookByISBN(isbn.get(),status);
    	
    	String location = "/books";// + book.getuIsbn();
    	String location1= "/books/" + book.getIsbn() + "/reviews";
        if(reply==true){
    	
//    	Book savedBook = bookRepository.saveBook();
    	//BookDto bookResponse = new BookDto(book);s
    	LinksDto bookResponse = new LinksDto();
    	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
    	bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
    	bookResponse.addLink(new LinkDto("create-review", location1, "POST"));
    	bookResponse.addLink(new LinkDto("view-all-reviews", location1, "GET"));
    	
    return Response.status(200).entity(bookResponse).build();}
        else
        
        	return Response.status(406).build();
    
  }
    
}
    