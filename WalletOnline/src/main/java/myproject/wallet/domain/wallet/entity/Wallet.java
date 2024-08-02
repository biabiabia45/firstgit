package myproject.wallet.domain.wallet.entity;

import myproject.wallet.domain.exceptions.InsufficientFundsException;
import myproject.wallet.domain.exceptions.InvalidAmountException;
import myproject.wallet.domain.valueobject.Money;

import java.math.BigDecimal;

public class Wallet {

    private Long id;
    private Long userId;

    private Money balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    // Business logic
    public void deposit(Money amount) {
        if (amount == null || amount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        if (amount == null || amount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (this.balance.getAmount().compareTo(amount.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }
}
