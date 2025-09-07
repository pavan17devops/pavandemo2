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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LibraryManager.BookLibraryManager.Dto.LoginRequest;
import LibraryManager.BookLibraryManager.Dto.Structure;
import LibraryManager.BookLibraryManager.Entity.User;
import LibraryManager.BookLibraryManager.Service.UserService;

@RestController
@RequestMapping("/api")

public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/userent")
	public User saveuser(@RequestBody User user) {
		return service.saveuser(user);
	}
	
	//Get all users
	@GetMapping("/userent")
	public ResponseEntity<Structure<List<User>>> Getallusers() {
		return service.Getallusers();
	}
	
	//Get a user by ID
	@GetMapping("/userent/{id}")
	public ResponseEntity<Structure<Optional<User>>> userbyid(@PathVariable Long id){
		return service.getUserById(id);
		
	}
	
	//Update a user by ID
	@PutMapping("/userent")
	public ResponseEntity<Structure<User>> Update(@RequestBody User user) {
		return service.updateaUserByID(user);
	}
	//Delete a user by ID
	@DeleteMapping("/userent/{id}")
	public ResponseEntity<Structure<Optional<User>>> deleteUser(@PathVariable Long id){
		return service.deleteaUserByID(id);
		
	}
	//Find By UserId and Password
	@PostMapping("/userent/validation")
	public ResponseEntity<Structure<Optional<User>>> login(@RequestBody LoginRequest loginRequest) {
	    return service.findByUserIdandPassword(loginRequest.getUsername(), loginRequest.getPassword());
	}
}
