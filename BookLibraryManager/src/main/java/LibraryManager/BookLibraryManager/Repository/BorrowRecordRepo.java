package LibraryManager.BookLibraryManager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LibraryManager.BookLibraryManager.Entity.BorrowRecord;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long>{
	List<BorrowRecord> findByUserId(Long id);
	List<BorrowRecord> findByBookId(Long id);
}

