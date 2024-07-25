package myproject.wallet.domain.security;

import java.util.Optional;
import java.util.UUID;

public class SecurityUserService {
    private final SecurityUserRepository userRepository;

    public SecurityUserService(SecurityUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<SecurityUser> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<SecurityUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(SecurityUser user) {
        userRepository.save(user);
        // Publish UserAuthenticatedEvent or UserAuthorizationEvent if needed
    }

    public void updateUser(SecurityUser user) {
        userRepository.save(user);
        // Publish UserUpdatedEvent if needed
    }
}
