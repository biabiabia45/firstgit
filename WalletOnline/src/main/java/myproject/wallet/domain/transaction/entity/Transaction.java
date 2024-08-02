package myproject.wallet.domain.transaction.entity;

import myproject.wallet.domain.exceptions.InsufficientFundsException;
import myproject.wallet.domain.valueobject.Money;
import myproject.wallet.domain.wallet.entity.Wallet;

import java.math.BigDecimal;

public class Transaction {

    private Long id;
    private Long sourceWalletId;
    private Long targetWalletId;
    private Money amount;

    public Transaction(Long sourceWalletId, Long targetWalletId, Money amount) {
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
    }

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(Long sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public Long getTargetWalletId() {
        return targetWalletId;
    }

    public void setTargetWalletId(Long targetWalletId) {
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
