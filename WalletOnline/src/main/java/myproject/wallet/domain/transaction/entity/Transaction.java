package myproject.wallet.domain.transaction.entity;

import jakarta.persistence.*;
import myproject.wallet.domain.transaction.valueobject.Money;
import myproject.wallet.domain.transaction.valueobject.WalletId;

import java.util.UUID;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Embedded
    private WalletId sourceWalletId;
    @Embedded
    private WalletId targetWalletId;
    @Embedded
    private Money amount;

    public Transaction(UUID id, WalletId sourceWalletId, WalletId targetWalletId, Money amount) {
        this.id = id;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
    }

    public Transaction() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WalletId getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(WalletId sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public WalletId getTargetWalletId() {
        return targetWalletId;
    }

    public void setTargetWalletId(WalletId targetWalletId) {
        this.targetWalletId = targetWalletId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}
