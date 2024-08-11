package myproject.wallet.domain.user.service;

import lombok.extern.slf4j.Slf4j;
import myproject.wallet.domain.exceptions.UserNotFoundException;
import myproject.wallet.domain.repository.UserRepository;
import myproject.wallet.domain.user.entity.User;
import myproject.wallet.domain.user.events.UserCreatedEvent;
import myproject.wallet.domain.user.events.UserDeletedEvent;
import myproject.wallet.domain.user.events.UserUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        try {
            UserCreatedEvent event = new UserCreatedEvent(savedUser.getId());
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("Failed to publish event or send message", e);
        }
        return savedUser;
    }


    public User updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User updatedUser = userRepository.save(user);
            try {
                UserUpdatedEvent event = new UserUpdatedEvent(user.getId());
                eventPublisher.publishEvent(event);
            } catch (Exception e) {
                log.error("Failed to publish event or send message", e);
            }
            return updatedUser;
        } else {
            throw new UserNotFoundException("User with id " + user.getId() + " not found.");
        }
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            try {
                UserDeletedEvent event = new UserDeletedEvent(id);
                eventPublisher.publishEvent(event);
            } catch (Exception e) {
                log.error("Failed to publish event or send message", e);
            }
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found."));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }
        user.updatePassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
