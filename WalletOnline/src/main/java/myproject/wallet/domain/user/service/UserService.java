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

    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        // 检查用户名是否唯一
        if (userRepository.existsByUserName(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        try {
            UserCreatedEvent event = new UserCreatedEvent(savedUser.getId());
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("Failed to publish user created event", e);
        }

        return savedUser;
    }


    public User updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            user.setContactInfo(user.getContactInfo());
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

    public void deleteUser(String username) {
        if (userRepository.existsByUserName(username)) {
            userRepository.deleteByUsername(username);
            try {
                UserDeletedEvent event = new UserDeletedEvent(username);
                eventPublisher.publishEvent(event);
            } catch (Exception e) {
                log.error("Failed to publish event or send message", e);
            }
        } else {
            throw new UserNotFoundException("User with username " + username + " not found.");
        }
    }

    public void changePassword(String userName, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User with username " + userName + " not found."));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }
        userRepository.changePassword(userName, user.updatePassword(passwordEncoder.encode(newPassword)));
    }
}
