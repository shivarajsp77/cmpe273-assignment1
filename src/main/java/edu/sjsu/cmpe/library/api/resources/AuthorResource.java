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
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;



    @Path("/v1/books/{isbn}/authors")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public class AuthorResource {
	
	private final BookRepositoryInterface bookRepository;

    public AuthorResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }
    	
	@GET
	@Path("/{ID}/")
    @Timed(name = "view-author")
    public Response viewauthor(@PathParam("isbn") LongParam isbn,@PathParam("ID") int ID) {
	   	Book book = bookRepository.getBookByISBN(isbn.get());
    	Author author = bookRepository.getAuthByISBNID(isbn.get(), ID);
        String location = "/books/" + book.getIsbn() + "/authors/" + ID;
    	AuthorDto bookResponse = new AuthorDto(author);
	    bookResponse.addLink(new LinkDto("view-author", location, "GET"));
	
	return Response.status(200).entity(bookResponse).build();
    }
	
	@GET
	@Timed(name="view-all-authors")
	
		 public Response viewallauthor(@PathParam("isbn") LongParam isbn) {
			   	Book book = bookRepository.getBookByISBN(isbn.get());
		    	List<Author> author = book.getAuthlist();
		        String location = "/books/" + book.getIsbn() + "/authors/";
		    	AuthorsDto bookResponse = new AuthorsDto(author);
		    	
			return Response.status(200).entity(bookResponse).build();
		    }
	
	}

