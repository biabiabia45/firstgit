package myproject.wallet.domain.events;

import java.util.UUID;

public class WalletDeletedEvent {
    private UUID walletId;

    public WalletDeletedEvent(UUID walletId) {
        this.walletId = walletId;
    }

    public UUID getWalletId() {
        return walletId;
    }

    @Override
    public String toString() {
        return "WalletDeletedEvent{walletId=" + walletId + "}";
    }
}
