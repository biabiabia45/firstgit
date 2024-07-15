package mycode.WalletOnline.WalletApplication.controller;

import mycode.WalletOnline.WalletApplication.dto.LoginDto;
import mycode.WalletOnline.WalletApplication.dto.UserDto;
import mycode.WalletOnline.WalletApplication.model.User;
import mycode.WalletOnline.WalletApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        User newUser = userService.registerUser(userDto);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        String result = userService.loginUser(loginDto);
        return ResponseEntity.ok(result);
    }
}
