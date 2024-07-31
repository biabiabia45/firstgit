package myproject.wallet.domain.events;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionTreansferedEvent {

    private final UUID transactionId;
    private final UUID sourceWalletId;
    private final UUID targetWalletId;
    private final BigDecimal amount;

    public TransactionTreansferedEvent(UUID transactionId, UUID sourceWalletId, UUID targetWalletId, BigDecimal amount) {
        this.transactionId = transactionId;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public UUID getSourceWalletId() {
        return sourceWalletId;
    }

    public UUID getTargetWalletId() {
        return targetWalletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
