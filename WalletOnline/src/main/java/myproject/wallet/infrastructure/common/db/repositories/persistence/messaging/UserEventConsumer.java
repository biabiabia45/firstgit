package myproject.wallet.infrastructure.common.db.repositories.persistence.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserEventConsumer.class);

    @KafkaListener(topics = "${kafka.user-created-topic}", groupId = "${kafka.user-group-id}")
    public void consumeUserCreatedEvent(String message) {
        logger.info("Received UserCreatedEvent: {}", message);
        // 处理 UserCreatedEvent
    }

    @KafkaListener(topics = "${kafka.user-updated-topic}", groupId = "${kafka.user-group-id}")
    public void consumeUserUpdatedEvent(String message) {
        logger.info("Received UserUpdatedEvent: {}", message);
        // 处理 UserUpdatedEvent
    }

    @KafkaListener(topics = "${kafka.user-deleted-topic}", groupId = "${kafka.user-group-id}")
    public void consumeUserDeletedEvent(String message) {
        logger.info("Received UserDeletedEvent: {}", message);
        // 处理 UserDeletedEvent
    }
}
