package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;

public class BookRepository implements BookRepositoryInterface {
    private static final String NULL = null;

	/** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;

    /** Never access this key directly; instead use generateISBNKey() */
    private static int authorId;
    private static int count;
    private static int reviewId;
    private long isbnKey;
    
    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
    }

    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	// increment existing isbnKey and return the new value
	return Long.valueOf(++isbnKey);
    }

    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Book saveBook(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	List<Author> newauthor = newBook.getAuthlist();
	
	// Generate new ISBN
	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);

	for (int i=0;i<newauthor.size();i++)
    {
    	Author tempauthor = newauthor.get(i);
    	tempauthor.setId(genauthorId());
    }
    
   	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
	
    }

    /**
     * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
     */
    @Override
    public Book getBookByISBN(Long isbn) {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
    }

    
    /**
     * This will delete unique ISBN for new books.
     */
    
    public boolean delBookByISBN(Long isbn) {
    	
    	if (bookInMemoryMap.containsKey(isbn)==true){
	
	       bookInMemoryMap.remove(isbn);
    	return true;
    	}
    return false;
    }
    
    public boolean updateBookByISBN(Long isbn,String status) {
    	
    	if (bookInMemoryMap.containsKey(isbn)){
    		
    		Book book = bookInMemoryMap.get(isbn);
    		book.setStatus(status);
 	     
     	return true;
     	
     	}
    	
     return false;
    }
    	
    private final int genauthorId(){
    	return ++authorId;}
    	
    	private final int genreviewId(){
        	return ++reviewId;	
    }
    
    
    public Book saveReview(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	List<Review> newreview = newBook.getReviewlist();

	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);

    for (int i=0;i<newreview.size();i++)
    {
    	Review tempreview = newreview.get(i);
    	tempreview.setID(genreviewId());
    }
    
    
	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }

	
public Review getRevByISBNID(Long isbn,int ID) {
    	
	Book book = bookInMemoryMap.get(isbn);
	List<Review> newreview = book.getReviewlist();
	
	for(int i=0;i<newreview.size();i++)
	{
		if(newreview.get(i).getID()==ID)
		{
			return newreview.get(i);
		}
	}
	return null;
}

public Author getAuthByISBNID(Long isbn,int id) {
	
	Book book = bookInMemoryMap.get(isbn);
	List<Author> newauthor = book.getAuthlist();
	
	for(int i=0;i<newauthor.size();i++)
	{
		if(newauthor.get(i).getId()==id)
			
			return newauthor.get(i);
	}
	return null;

}

}
		
    
    

