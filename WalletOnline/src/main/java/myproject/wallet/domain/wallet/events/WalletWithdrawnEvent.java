package myproject.wallet.domain.wallet.events;

import myproject.wallet.domain.valueobject.Money;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletWithdrawnEvent {
    private Long walletId;
    private Money amount;

    public WalletWithdrawnEvent(Long walletId, Money amount) {
        this.walletId = walletId;
        this.amount = amount;
    }

    public Long getWalletId() {
        return walletId;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "WalletWithdrawnEvent{walletId=" + walletId + ", amount=" + amount + "}";
    }
}
