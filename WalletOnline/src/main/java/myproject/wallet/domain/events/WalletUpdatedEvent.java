package myproject.wallet.domain.events;

import java.util.UUID;

public class WalletUpdatedEvent {
    private UUID walletId;

    public WalletUpdatedEvent(UUID walletId) {
        this.walletId = walletId;
    }

    public UUID getWalletId() {
        return walletId;
    }

    @Override
    public String toString() {
        return "WalletUpdatedEvent{walletId=" + walletId + "}";
    }
}
