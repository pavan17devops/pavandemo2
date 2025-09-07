package LibraryManager.BookLibraryManager.Service;
//import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import LibraryManager.BookLibraryManager.Dto.Structure;
//import LibraryManager.BookLibraryManager.Entity.Book;
//import LibraryManager.BookLibraryManager.Entity.BorrowRecord;
//import LibraryManager.BookLibraryManager.Entity.User;
//import LibraryManager.BookLibraryManager.Repository.BookRepo;
//import LibraryManager.BookLibraryManager.Repository.BorrowRecordRepo;
//import LibraryManager.BookLibraryManager.Repository.UserRepo;
//
//@Service
//public class BorrowRecordService {
//
//    @Autowired
//    private BorrowRecordRepo recordRepo;
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private BookRepo bookRepo;
//
//    public ResponseEntity<Structure<BorrowRecord>> saveBorrowRecord(BorrowRecord borrowRecord) {
//        // Extract IDs
//        Long userId = borrowRecord.getUser().getId();
//        Long bookId = borrowRecord.getBook().getId();
//
//        // Load User
//        User user = userRepo.findById(userId).orElse(null);
//        if (user == null) {
//            throw new RuntimeException("User not found with ID: " + userId);
//        }
//
//        // Load Book
//        Book book = bookRepo.findById(bookId).orElse(null);
//        if (book == null) {
//            throw new RuntimeException("Book not found with ID: " + bookId);
//        }
//
//        // Set loaded entities
//        borrowRecord.setUser(user);
//        borrowRecord.setBook(book);
//
//        // Save record
//        BorrowRecord saved = recordRepo.save(borrowRecord);
//
//        // Wrap in structure
//        Structure<BorrowRecord> str = new Structure<>();
//        str.setStatuscode(HttpStatus.OK.value());
//        str.setData(saved);
//
//        return new ResponseEntity<>(str, HttpStatus.OK);
//    }
//}

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import LibraryManager.BookLibraryManager.Dto.Structure;
import LibraryManager.BookLibraryManager.Entity.Book;
import LibraryManager.BookLibraryManager.Entity.BorrowRecord;
import LibraryManager.BookLibraryManager.Entity.User;
import LibraryManager.BookLibraryManager.Repository.BookRepo;
import LibraryManager.BookLibraryManager.Repository.BorrowRecordRepo;
import LibraryManager.BookLibraryManager.Repository.UserRepo;

@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordRepo recordRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    public ResponseEntity<Structure<BorrowRecord>> saveBorrowRecord(BorrowRecord borrowRecord) {
        if (borrowRecord.getUser() != null && borrowRecord.getUser().getId() != 0) {
            Optional<User> userOpt = userRepo.findById(borrowRecord.getUser().getId());
            if (userOpt.isPresent()) {
                borrowRecord.setUser(userOpt.get());
            } else {
                throw new RuntimeException("User not found with ID: " + borrowRecord.getUser().getId());
            }
        }

        if (borrowRecord.getBook() != null && borrowRecord.getBook().getId() != 0) {
            Optional<Book> bookOpt = bookRepo.findById(borrowRecord.getBook().getId());
            if (bookOpt.isPresent()) {
                borrowRecord.setBook(bookOpt.get());
            } else {
                throw new RuntimeException("Book not found with ID: " + borrowRecord.getBook().getId());
            }
        }

        BorrowRecord savedRecord = recordRepo.save(borrowRecord);

        Structure<BorrowRecord> structure = new Structure<>();
        structure.setStatuscode(HttpStatus.CREATED.value());
        structure.setData(savedRecord);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

	public ResponseEntity<Structure<List<BorrowRecord>>> allBorrowRecords() {
		List<BorrowRecord> allrecords = recordRepo.findAll();
		Structure<List<BorrowRecord>> str = new Structure<List<BorrowRecord>>();
		str.setStatuscode(HttpStatus.OK.value());
		str.setData(allrecords);
		return new ResponseEntity<Structure<List<BorrowRecord>>>(str,HttpStatus.OK);
	}

	public ResponseEntity<Structure<Optional<BorrowRecord>>> getBorrowRecordByID(Long id) {
		Optional<BorrowRecord>opt = recordRepo.findById(id);
		Structure<Optional<BorrowRecord>> str = new Structure<Optional<BorrowRecord>>();
		if (opt.isPresent()) {
			str.setStatuscode(HttpStatus.OK.value());
			str.setData(opt);
			return new ResponseEntity<Structure<Optional<BorrowRecord>>>(str, HttpStatus.OK);
		}
		else {
			str.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Structure<Optional<BorrowRecord>>>(str,HttpStatus.NOT_FOUND);	 
		}
	}

	public ResponseEntity<Structure<BorrowRecord>> updateaBorrowRecord(BorrowRecord borrowRecord) {
		BorrowRecord update = recordRepo.save(borrowRecord);
		Structure<BorrowRecord> str = new Structure<BorrowRecord>();
		str.setStatuscode(HttpStatus.OK.value());
		str.setData(update);
		return new ResponseEntity<Structure<BorrowRecord>>(str, HttpStatus.OK);
	}

	public ResponseEntity<Structure<Optional<BorrowRecord>>> deleteaBorrowRecord(Long id) {
		Optional<BorrowRecord>opt = recordRepo.findById(id);
		Structure<Optional<BorrowRecord>>str = new Structure<Optional<BorrowRecord>>();
		if (opt.isPresent()) {
			recordRepo.delete(opt.get());
			str.setStatuscode(HttpStatus.OK.value());
			str.setData(opt);
			return new ResponseEntity<Structure<Optional<BorrowRecord>>>(str, HttpStatus.OK);
		}
		else {
			str.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Structure<Optional<BorrowRecord>>>(str,HttpStatus.NOT_FOUND);	 
		}
	}

	//Get all borrow records by a user
	public ResponseEntity<Structure<List<BorrowRecord>>> getAllBorrowRecordsByUser(Long id) {
	    Optional<User> optUser = userRepo.findById(id);
	    Structure<List<BorrowRecord>> str = new Structure<List<BorrowRecord>>();
	    
	    if (optUser.isPresent()) {
	        List<BorrowRecord> records = recordRepo.findByUserId(id);
	        str.setStatuscode(HttpStatus.OK.value());
	        str.setData(records);
	        return new ResponseEntity<Structure<List<BorrowRecord>>>(str, HttpStatus.OK);
	    } else {
	        str.setStatuscode(HttpStatus.NOT_FOUND.value());
	        str.setData(null);
	        return new ResponseEntity<Structure<List<BorrowRecord>>>(str, HttpStatus.OK);
	    }
	}
	
	//Get all borrow records for a specific book
	public ResponseEntity<Structure<List<BorrowRecord>>>getAllBorrowRecordsForSpecificBook(Long id){
		Optional<Book> optBook = bookRepo.findById(id);
		Structure<List<BorrowRecord>> str = new Structure<List<BorrowRecord>>();
		if (optBook.isPresent()) {
			List<BorrowRecord> Brecords = recordRepo.findByBookId(id);
			str.setStatuscode(HttpStatus.OK.value());
			str.setData(Brecords);
			return new ResponseEntity<Structure<List<BorrowRecord>>>(str, HttpStatus.OK);
		}else {
			str.setStatuscode(HttpStatus.NOT_FOUND.value());
			str.setData(null);
			return new ResponseEntity<Structure<List<BorrowRecord>>>(str, HttpStatus.NOT_FOUND);
		}
		
	}

}
