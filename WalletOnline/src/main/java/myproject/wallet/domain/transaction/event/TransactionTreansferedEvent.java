package myproject.wallet.domain.transaction.event;

import myproject.wallet.domain.valueobject.Money;

import java.util.UUID;

public class TransactionTreansferedEvent {

    private final UUID transactionId;
    private final UUID sourceWalletId;
    private final UUID targetWalletId;
    private final Money amount;

    public TransactionTreansferedEvent(UUID transactionId, UUID sourceWalletId, UUID targetWalletId, Money amount) {
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

    public Money getAmount() {
        return amount;
    }
}
