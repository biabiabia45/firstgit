package myproject.wallet.domain.wallet.entity;

import jakarta.persistence.*;
import myproject.wallet.domain.exceptions.InsufficientFundsException;
import myproject.wallet.domain.exceptions.InvalidAmountException;
import myproject.wallet.domain.wallet.valueobject.Money;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    @Embedded
    private Money balance;

    // Constructors
    public Wallet() {
    }

    public Wallet(UUID id, UUID userId, Money balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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
