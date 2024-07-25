package myproject.wallet.domain.wallet.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class WalletEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String walletCreatedTopic = "wallet-created";
    private final String walletUpdatedTopic = "wallet-updated";
    private final String walletDeletedTopic = "wallet-deleted";

    public WalletEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWalletCreatedEvent(String event) {
        kafkaTemplate.send(walletCreatedTopic, event);
    }

    public void sendWalletUpdatedEvent(String event) {
        kafkaTemplate.send(walletUpdatedTopic, event);
    }

    public void sendWalletDeletedEvent(String event) {
        kafkaTemplate.send(walletDeletedTopic, event);
    }
}
