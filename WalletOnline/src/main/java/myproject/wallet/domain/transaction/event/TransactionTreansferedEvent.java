package myproject.wallet.domain.transaction.event;

import myproject.wallet.domain.transaction.valueobject.Money;
import myproject.wallet.domain.transaction.valueobject.WalletId;

import java.util.UUID;

public class TransactionTreansferedEvent {

    private final UUID transactionId;
    private final WalletId sourceWalletId;
    private final WalletId targetWalletId;
    private final Money amount;

    public TransactionTreansferedEvent(UUID transactionId, WalletId sourceWalletId, WalletId targetWalletId, Money amount) {
        this.transactionId = transactionId;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public WalletId getSourceWalletId() {
        return sourceWalletId;
    }

    public WalletId getTargetWalletId() {
        return targetWalletId;
    }

    public Money getAmount() {
        return amount;
    }
}
