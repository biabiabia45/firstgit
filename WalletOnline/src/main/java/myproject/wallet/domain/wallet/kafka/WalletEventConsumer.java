package myproject.wallet.domain.wallet.kafka;

import myproject.wallet.domain.wallet.WalletService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WalletEventConsumer {

    private final WalletService walletService;

    public WalletEventConsumer(WalletService walletService) {
        this.walletService = walletService;
    }

    @KafkaListener(topics = "mywallet-topic", groupId = "wallet-group")
    public void handleMessage(String message) {
        // Parse the message to handle different types of events
        // This can be done using a message format or event type identifier

        // Assuming a basic message handling for demonstration
        if (message.contains("WALLET_CREATED")) {
            // Handle WalletCreatedEvent
            handleWalletCreatedEvent(message);
        } else if (message.contains("WALLET_UPDATED")) {
            // Handle WalletUpdatedEvent
            handleWalletUpdatedEvent(message);
        } else if (message.contains("WALLET_DELETED")) {
            // Handle WalletDeletedEvent
            handleWalletDeletedEvent(message);
        } else if (message.contains("WALLET_DEPOSITED")) {
            // Handle WalletDepositedEvent
            handleWalletDepositedEvent(message);
        } else if (message.contains("WALLET_WITHDRAWN")) {
            // Handle WalletWithdrawnEvent
            handleWalletWithdrawnEvent(message);
        }
    }

    private void handleWalletCreatedEvent(String message) {
        // Implement your logic to handle WalletCreatedEvent
        System.out.println("Handling WalletCreatedEvent: " + message);
    }

    private void handleWalletUpdatedEvent(String message) {
        // Implement your logic to handle WalletUpdatedEvent
        System.out.println("Handling WalletUpdatedEvent: " + message);
    }

    private void handleWalletDeletedEvent(String message) {
        // Implement your logic to handle WalletDeletedEvent
        System.out.println("Handling WalletDeletedEvent: " + message);
    }

    private void handleWalletDepositedEvent(String message) {
        // Implement your logic to handle WalletDepositedEvent
        System.out.println("Handling WalletDepositedEvent: " + message);
    }

    private void handleWalletWithdrawnEvent(String message) {
        // Implement your logic to handle WalletWithdrawnEvent
        System.out.println("Handling WalletWithdrawnEvent: " + message);
    }
}
