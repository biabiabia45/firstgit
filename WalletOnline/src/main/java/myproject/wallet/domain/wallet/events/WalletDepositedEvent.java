package myproject.wallet.domain.wallet.events;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletDepositedEvent {
    private UUID walletId;
    private BigDecimal amount;

    public WalletDepositedEvent(UUID walletId, BigDecimal amount) {
        this.walletId = walletId;
        this.amount = amount;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "WalletDepositedEvent{walletId=" + walletId + ", amount=" + amount + "}";
    }
}
