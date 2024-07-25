package myproject.wallet.domain.wallet.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WalletEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(WalletEventConsumer.class);

    @KafkaListener(topics = "${kafka.wallet-created-topic}", groupId = "${kafka.wallet-group-id}")
    public void consumeWalletCreatedEvent(String message) {
        logger.info("Received WalletCreatedEvent: {}", message);
        // 处理 WalletCreatedEvent
    }

    @KafkaListener(topics = "${kafka.wallet-updated-topic}", groupId = "${kafka.wallet-group-id}")
    public void consumeWalletUpdatedEvent(String message) {
        logger.info("Received WalletUpdatedEvent: {}", message);
        // 处理 WalletUpdatedEvent
    }

    @KafkaListener(topics = "${kafka.wallet-deleted-topic}", groupId = "${kafka.wallet-group-id}")
    public void consumeWalletDeletedEvent(String message) {
        logger.info("Received WalletDeletedEvent: {}", message);
        // 处理 WalletDeletedEvent
    }

    @KafkaListener(topics = "${kafka.wallet-deposited-topic}", groupId = "${kafka.wallet-group-id}")
    public void consumeWalletDepositedEvent(String message) {
        logger.info("Received WalletDepositedEvent: {}", message);
        // 处理 WalletDepositedEvent
    }

    @KafkaListener(topics = "${kafka.wallet-withdrawn-topic}", groupId = "${kafka.wallet-group-id}")
    public void consumeWalletWithdrawnEvent(String message) {
        logger.info("Received WalletWithdrawnEvent: {}", message);
        // 处理 WalletWithdrawnEvent
    }
}
