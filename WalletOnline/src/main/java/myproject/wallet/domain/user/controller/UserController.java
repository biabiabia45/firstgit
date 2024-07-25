package myproject.wallet.domain.user.controller;

import lombok.extern.slf4j.Slf4j;
import myproject.wallet.domain.user.User;
import myproject.wallet.domain.user.UserService;
import myproject.wallet.domain.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/wallet-online/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } catch (Exception e) {
            log.error("Error creating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        user.setId(id);
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser); // 200 OK
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (Exception e) {
            log.error("Error updating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (Exception e) {
            log.error("Error deleting user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}

