package myproject.wallet.domain.transaction.entity;

import jakarta.persistence.*;
import myproject.wallet.domain.valueobject.Money;
import myproject.wallet.domain.wallet.entity.Wallet;
import myproject.wallet.domain.exceptions.InsufficientFundsException;
import myproject.wallet.domain.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID sourceWalletId;
    private UUID targetWalletId;
    @Embedded
    private Money amount;

    public Transaction(UUID id, UUID sourceWalletId, UUID targetWalletId, Money amount) {
        this.id = id;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
    }

    public Transaction() {}

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(UUID sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public UUID getTargetWalletId() {
        return targetWalletId;
    }

    public void setTargetWalletId(UUID targetWalletId) {
        this.targetWalletId = targetWalletId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public void validateTransferAmount() {
        if (amount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
    }

    public void executeTransfer(Wallet sourceWallet, Wallet targetWallet) {
        if (sourceWallet.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in source wallet");
        }

        sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
        targetWallet.setBalance(targetWallet.getBalance().add(amount));
    }
}
