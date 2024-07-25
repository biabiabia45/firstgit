package myproject.wallet.domain.wallet.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WalletEventConsumer {

    @KafkaListener(topics = "wallet-created", groupId = "wallet-group")
    public void listenWalletCreated(String message) {
        // Handle the wallet created event
        System.out.println("Received Wallet Created Event: " + message);
        // Implement event handling logic
    }

    @KafkaListener(topics = "wallet-updated", groupId = "wallet-group")
    public void listenWalletUpdated(String message) {
        // Handle the wallet updated event
        System.out.println("Received Wallet Updated Event: " + message);
        // Implement event handling logic
    }

    @KafkaListener(topics = "wallet-deleted", groupId = "wallet-group")
    public void listenWalletDeleted(String message) {
        // Handle the wallet deleted event
        System.out.println("Received Wallet Deleted Event: " + message);
        // Implement event handling logic
    }
}
