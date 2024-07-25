package myproject.wallet.domain.wallet.events;

import java.util.UUID;

public class WalletDeletedEvent {
    private UUID walletId;

    public WalletDeletedEvent(UUID walletId) {
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
        return "WalletDeletedEvent{" +
                "walletId=" + walletId +
                '}';
    }
}
