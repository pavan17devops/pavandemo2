package LibraryManager.BookLibraryManager.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import LibraryManager.BookLibraryManager.Dto.Structure;
import LibraryManager.BookLibraryManager.Entity.User;
import LibraryManager.BookLibraryManager.Repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BCryptPasswordEncoder cryptPasswordEncoder;

	// SaveUser
	public User saveuser(User user) {
		user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user);

	}

	// Get all users
	public ResponseEntity<Structure<List<User>>> Getallusers() {
		List<User> allusers = userRepo.findAll();
		Structure<List<User>> str = new Structure<List<User>>();
		str.setStatuscode(HttpStatus.OK.value());
		str.setData(allusers);
		return new ResponseEntity<Structure<List<User>>>(str, HttpStatus.OK);

	}

	// Get a user by ID
	public ResponseEntity<Structure<Optional<User>>> getUserById(Long id) {
		Optional<User> opt = userRepo.findById(id);
		Structure<Optional<User>> str = new Structure<>();

		if (opt.isPresent()) {
			str.setStatuscode(HttpStatus.OK.value());
			str.setData(opt);
			return new ResponseEntity<>(str, HttpStatus.OK);
		}

		str.setStatuscode(HttpStatus.NOT_FOUND.value());
		str.setData(Optional.empty());
		return new ResponseEntity<>(str, HttpStatus.NOT_FOUND);
	}

	// Update a user by ID
	public ResponseEntity<Structure<User>> updateaUserByID(User user) {
		User update = userRepo.save(user);
		Structure<User> str = new Structure<>();
		str.setStatuscode(HttpStatus.OK.value());
		str.setData(update);
		return new ResponseEntity<Structure<User>>(str, HttpStatus.OK);

	}

	// Delete a user by ID
	public ResponseEntity<Structure<Optional<User>>> deleteaUserByID(Long id) {
		Optional<User> opt = userRepo.findById(id);
		Structure<Optional<User>> str = new Structure<Optional<User>>();
		if (opt.isPresent()) {
			userRepo.delete(opt.get());
			str.setStatuscode(HttpStatus.OK.value());
			str.setData(opt);
			return new ResponseEntity<Structure<Optional<User>>>(str, HttpStatus.OK);

		}
		return null;
	}

	// Find By UserId and Password
	// Find By UserId and Password
	public ResponseEntity<Structure<Optional<User>>> findByUserIdandPassword(String username, String password) {
	    Optional<User> opt = userRepo.findByUsername(username);
	    Structure<Optional<User>> str = new Structure<>();

	    if (opt.isPresent()) {
	        User user = opt.get();
	        if (cryptPasswordEncoder.matches(password, user.getPassword())) {
	            str.setStatuscode(HttpStatus.OK.value());
	            str.setData(Optional.of(user));
	            return new ResponseEntity<>(str, HttpStatus.OK);
	        } else {
	            str.setStatuscode(HttpStatus.UNAUTHORIZED.value());
	            str.setData(Optional.empty());
	            return new ResponseEntity<>(str, HttpStatus.UNAUTHORIZED);
	        }
	    } else {
	        str.setStatuscode(HttpStatus.NOT_FOUND.value());
	        str.setData(Optional.empty());
	        return new ResponseEntity<>(str, HttpStatus.NOT_FOUND);
	    }
	}


}
