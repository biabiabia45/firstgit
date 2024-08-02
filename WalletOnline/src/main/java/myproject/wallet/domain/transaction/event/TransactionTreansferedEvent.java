package myproject.wallet.domain.transaction.event;

import myproject.wallet.domain.valueobject.Money;

public class TransactionTreansferedEvent {

    private final Long transactionId;
    private final Long sourceWalletId;
    private final Long targetWalletId;
    private final Money amount;

    public TransactionTreansferedEvent(Long transactionId, Long sourceWalletId, Long targetWalletId, Money amount) {
        this.transactionId = transactionId;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getSourceWalletId() {
        return sourceWalletId;
    }

    public Long getTargetWalletId() {
        return targetWalletId;
    }

    public Money getAmount() {
        return amount;
    }
}
