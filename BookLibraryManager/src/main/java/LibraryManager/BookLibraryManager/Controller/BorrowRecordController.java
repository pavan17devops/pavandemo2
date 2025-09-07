package LibraryManager.BookLibraryManager.Controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import LibraryManager.BookLibraryManager.Dto.Structure;
import LibraryManager.BookLibraryManager.Entity.BorrowRecord;
import LibraryManager.BookLibraryManager.Service.BorrowRecordService;
@RestController
public class BorrowRecordController {
	@Autowired
	private BorrowRecordService recordService;
	//Save BorrowRecord
	@PostMapping("/BorrowRecord")
	public ResponseEntity<Structure<BorrowRecord>> saveBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
		return recordService.saveBorrowRecord(borrowRecord);
	}
	//Get all borrow records
	@GetMapping("/BorrowRecord")
	public ResponseEntity<Structure< List<BorrowRecord>>>getAllBorrowRecords(){
		return recordService.allBorrowRecords();
	}
	
	//Get a borrow record by ID
	@GetMapping("/BorrowRecord/{id}")
	public ResponseEntity<Structure<Optional<BorrowRecord>>> borrowrecordbyID(@PathVariable Long id){
		return recordService.getBorrowRecordByID(id);
		
	}
	
	//Update a borrow record
	@PutMapping("/BorrowRecord")
	public ResponseEntity<Structure<BorrowRecord>>Update(@RequestBody BorrowRecord borrowRecord){
		return recordService.updateaBorrowRecord(borrowRecord);
	}
	
	//Delete a borrow record
	@DeleteMapping("/BorrowRecord/{id}")
	public ResponseEntity<Structure<Optional<BorrowRecord>>>deleteRecord(@PathVariable Long id){
		return recordService.deleteaBorrowRecord(id);
	}
	//Get all borrow records by a user
	@GetMapping("/BorrowRecord/user/{id}")
	public ResponseEntity<Structure<List<BorrowRecord>>> getAllBorrowRecordsByUser(@PathVariable Long id) {
		return recordService.getAllBorrowRecordsByUser(id);
		
	}
	//Get all borrow records by a book
	@GetMapping("/BorrowRecord/book/{id}")
	public ResponseEntity<Structure<List<BorrowRecord>>> getAllBorrowRecordsByBook(@PathVariable Long id) {
		return recordService.getAllBorrowRecordsForSpecificBook(id);
		
	}
	
}
