package myproject.wallet.domain.transaction.events;

import java.util.UUID;

public class TransactionUpdatedEvent {
    private final UUID transactionId;

    public TransactionUpdatedEvent(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }
}