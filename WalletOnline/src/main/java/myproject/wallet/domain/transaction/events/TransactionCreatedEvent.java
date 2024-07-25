package myproject.wallet.domain.transaction.events;

import java.util.UUID;

public class TransactionCreatedEvent {
    private final UUID transactionId;

    public TransactionCreatedEvent(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }
}
