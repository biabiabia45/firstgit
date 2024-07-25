package myproject.wallet.domain.wallet.events;

import java.util.UUID;

public class WalletUpdatedEvent {
    private UUID walletId;

    public WalletUpdatedEvent(UUID walletId) {
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
        return "WalletUpdatedEvent{" +
                "walletId=" + walletId +
                '}';
    }
}
