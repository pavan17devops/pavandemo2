package LibraryManager.BookLibraryManager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import LibraryManager.BookLibraryManager.Dto.Structure;
import LibraryManager.BookLibraryManager.Entity.Book;
import LibraryManager.BookLibraryManager.Repository.BookRepo;

@Service
public class BookService {
    
    @Autowired
    private BookRepo bookRepo;

    // Save book
    public ResponseEntity<Structure<Book>> savebook(Book book) {
    	Book save = bookRepo.save(book);  
    	Structure<Book> str = new Structure<Book>();
    	str.setStatuscode(HttpStatus.OK.value());
    	str.setData(save);
    	return new ResponseEntity<Structure<Book>>(str, HttpStatus.OK);
    }
    //Get all books
    public ResponseEntity<Structure<List<Book>>>Getallbooks(){
    	List<Book>getall = bookRepo.findAll();
    	Structure<List<Book>> str = new Structure<List<Book>>();
    	str.setStatuscode(HttpStatus.OK.value());
    	str.setData(getall);
		return new ResponseEntity<Structure<List<Book>>>(str, HttpStatus.OK);
    	
    }
    
    //Get a book by ID
    public ResponseEntity<Structure<Book>> Get_a_book_by_ID(long id) {
        Optional<Book> opt = bookRepo.findById(id);
        Structure<Book> response = new Structure<>();
        if (opt.isPresent()) {
            response.setStatuscode(HttpStatus.OK.value());
            response.setData(opt.get());
            return ResponseEntity.ok(response);
        } else {
            response.setStatuscode(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    //Update a book by ID
//   public ResponseEntity<Structure<Book>>UpdateabookbyID(Book book){
//	   Book update = bookRepo.save(book);
//	   Structure<Book>str = new Structure<Book>();
//	   str.setStatuscode(HttpStatus.UPGRADE_REQUIRED.value());
//	   str.setData(update);
//	   return new ResponseEntity<Structure<Book>>(str, HttpStatus.UPGRADE_REQUIRED);
//   }
   
   //Delete a book by ID
   public ResponseEntity<Structure<Optional<Book>>>DeleteabookbyID(Long id){
	   Optional<Book> opt = bookRepo.findById(id);
	   Structure<Book> response = new Structure<>();
	   if (opt.isPresent()) {
		   bookRepo.delete(opt.get());
		   response.setStatuscode(HttpStatus.OK.value());
		   response.setData(opt.get());
		   return new ResponseEntity<Structure<Optional<Book>>>(HttpStatus.OK);
	}
	return null;
   }

// Update a book by ID
   public ResponseEntity<Structure<Book>> updateBookById(Long id, Book book) {
       Optional<Book> optionalBook = bookRepo.findById(id);
       Structure<Book> response = new Structure<>();
       if (optionalBook.isPresent()) {
           Book existingBook = optionalBook.get();
           existingBook.setTitle(book.getTitle());
           existingBook.setAuthor(book.getAuthor());
           // Update other fields as needed
           Book updatedBook = bookRepo.save(existingBook);
           response.setStatuscode(HttpStatus.OK.value());
           response.setData(updatedBook);
           return new ResponseEntity<>(response, HttpStatus.OK);
       } else {
           response.setStatuscode(HttpStatus.NOT_FOUND.value());
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
   }
    
}
