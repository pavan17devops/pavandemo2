package LibraryManager.BookLibraryManager.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import LibraryManager.BookLibraryManager.Dto.Structure;
import LibraryManager.BookLibraryManager.Entity.Book;
import LibraryManager.BookLibraryManager.Service.BookService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // Save a book
    @PostMapping("/book")
    public ResponseEntity<Structure<Book>> saveBook(@Valid @RequestBody Book book) {
        return bookService.savebook(book);
    }

    // Get all books
    @GetMapping("/book")
    public ResponseEntity<Structure<List<Book>>> getAllBooks() {
        return bookService.Getallbooks();
    }

    // Get a book by ID
    @GetMapping("/book/{id}")
    public ResponseEntity<Structure<Book>> getBookById(@PathVariable Long id) {
        return bookService.Get_a_book_by_ID(id);
    }

    // Update a book by ID
    @PutMapping("/book/{id}")
    public ResponseEntity<Structure<Book>> updateBookById(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.updateBookById(id, book);
    }

    // Delete a book by ID
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Structure<Optional<Book>>> deleteBookById(@PathVariable Long id) {
        return bookService.DeleteabookbyID(id);
    }
}