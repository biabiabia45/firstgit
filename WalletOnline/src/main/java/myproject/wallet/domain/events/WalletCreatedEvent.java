package myproject.wallet.domain.events;

import java.util.UUID;

public class WalletCreatedEvent {
    private UUID walletId;

    public WalletCreatedEvent(UUID walletId) {
        this.walletId = walletId;
    }

    public UUID getWalletId() {
        return walletId;
    }

    @Override
    public String toString() {
        return "WalletCreatedEvent{walletId=" + walletId + "}";
    }
}
