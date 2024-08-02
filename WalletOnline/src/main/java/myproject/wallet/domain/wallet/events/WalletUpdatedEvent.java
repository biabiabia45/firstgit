package myproject.wallet.domain.wallet.events;

public class WalletUpdatedEvent {
    private Long walletId;

    public WalletUpdatedEvent(Long walletId) {
        this.walletId = walletId;
    }

    public Long getWalletId() {
        return walletId;
    }

    @Override
    public String toString() {
        return "WalletUpdatedEvent{walletId=" + walletId + "}";
    }
}
