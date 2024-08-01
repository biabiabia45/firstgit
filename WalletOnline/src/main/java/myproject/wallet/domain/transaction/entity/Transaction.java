package myproject.wallet.domain.transaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID sourceWalletId; // 来源钱包ID
    private UUID targetWalletId; // 目标钱包ID
    private BigDecimal amount; // 交易金额

    public Transaction(UUID id, UUID sourceWalletId, UUID targetWalletId, BigDecimal amount) {
        this.id = id;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
    }

    public Transaction() {

    }
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
