package myproject.wallet.domain.wallet.events;

import java.util.UUID;

public class WalletCreatedEvent {
    private UUID walletId;

    public WalletCreatedEvent(UUID walletId) {
        this.walletId = walletId;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    @Override
    public String toString() {
        return "WalletCreatedEvent{" +
                "walletId=" + walletId +
                '}';
    }
}
