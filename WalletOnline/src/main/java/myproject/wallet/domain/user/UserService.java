package myproject.wallet.domain.user;

import myproject.wallet.domain.user.events.UserCreatedEvent;
import myproject.wallet.domain.user.events.UserDeletedEvent;
import myproject.wallet.domain.user.events.UserUpdatedEvent;
import myproject.wallet.domain.user.kafka.UserEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserEventProducer userEventProducer;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher, UserEventProducer userEventProducer) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.userEventProducer = userEventProducer;
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        userRepository.save(user);
        UserCreatedEvent event = new UserCreatedEvent(user.getId());
        eventPublisher.publishEvent(event);
        userEventProducer.sendUserCreatedEvent(event.toString());
    }

    public void updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            UserUpdatedEvent event = new UserUpdatedEvent(user.getId());
            eventPublisher.publishEvent(event);
            userEventProducer.sendUserUpdatedEvent(event.toString());
        }
    }

    public void deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            UserDeletedEvent event = new UserDeletedEvent(id);
            eventPublisher.publishEvent(event);
            userEventProducer.sendUserDeletedEvent(event.toString());
        }
    }
}
