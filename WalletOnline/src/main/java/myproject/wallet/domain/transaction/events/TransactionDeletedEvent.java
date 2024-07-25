package myproject.wallet.domain.transaction.events;

import java.util.UUID;

public class TransactionDeletedEvent {
    private final UUID transactionId;

    public TransactionDeletedEvent(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }
}