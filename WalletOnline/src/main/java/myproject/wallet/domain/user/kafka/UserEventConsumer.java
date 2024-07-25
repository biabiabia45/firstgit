package myproject.wallet.domain.user.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @KafkaListener(topics = "user-created", groupId = "mygroup")
    public void consumeUserCreatedEvent(String event) {
        // 处理 UserCreatedEvent
    }

    @KafkaListener(topics = "user-updated", groupId = "mygroup")
    public void consumeUserUpdatedEvent(String event) {
        // 处理 UserUpdatedEvent
    }

    @KafkaListener(topics = "user-deleted", groupId = "mygroup")
    public void consumeUserDeletedEvent(String event) {
        // 处理 UserDeletedEvent
    }
}
