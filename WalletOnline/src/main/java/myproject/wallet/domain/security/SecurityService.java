package myproject.wallet.domain.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class SecurityService {
    private final SecurityUserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityService(SecurityUserService userService) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean authenticate(String username, String password) {
        return userService.getUserByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    public void registerUser(String username, String password, String role) {
        String hashedPassword = passwordEncoder.encode(password);
        SecurityUser newUser = new SecurityUser(UUID.randomUUID(), username, hashedPassword, role);
        userService.createUser(newUser);
        // Publish UserAuthenticatedEvent or UserAuthorizationEvent if needed
    }
}
