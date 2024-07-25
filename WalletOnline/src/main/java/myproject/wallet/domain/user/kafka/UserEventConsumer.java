package myproject.wallet.domain.user.kafka;

import myproject.wallet.domain.user.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    private final UserService userService;

    public UserEventConsumer() {
        this.userService = userService;
    }

    @KafkaListener(topics = "${kafka.user-topic}", groupId = "${kafka.group-id}")
    public void handleMessage(String message) {
        // Parse the message and handle the event
        // You may need to deserialize the message and handle different types of events

        if (message.contains("USER_CREATED")) {
            handleUserCreatedEvent(message);
        } else if (message.contains("USER_UPDATED")) {
            handleUserUpdatedEvent(message);
        } else if (message.contains("USER_DELETED")) {
            handleUserDeletedEvent(message);
        }
    }

    private void handleUserCreatedEvent(String message) {
        // Extract user ID from the message and perform appropriate actions
        String userId = extractUserId(message);
        // Call service method or perform other actions
        System.out.println("Handling UserCreatedEvent for user ID: " + userId);
        // userService.createUser(userId); // Example, adjust based on your logic
    }

    private void handleUserUpdatedEvent(String message) {
        // Extract user ID from the message and perform appropriate actions
        String userId = extractUserId(message);
        // Call service method or perform other actions
        System.out.println("Handling UserUpdatedEvent for user ID: " + userId);
        // userService.updateUser(userId); // Example, adjust based on your logic
    }

    private void handleUserDeletedEvent(String message) {
        // Extract user ID from the message and perform appropriate actions
        String userId = extractUserId(message);
        // Call service method or perform other actions
        System.out.println("Handling UserDeletedEvent for user ID: " + userId);
        // userService.deleteUser(userId); // Example, adjust based on your logic
    }

    private String extractUserId(String message) {
        // Implement logic to extract user ID from the message
        // This is just an example, adjust based on your message format
        return message.split(":")[1].trim();
    }
}
