package myproject.wallet.domain.events;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletWithdrawnEvent {
    private UUID walletId;
    private BigDecimal amount;

    public WalletWithdrawnEvent(UUID walletId, BigDecimal amount) {
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
        return "WalletWithdrawnEvent{walletId=" + walletId + ", amount=" + amount + "}";
    }
}
