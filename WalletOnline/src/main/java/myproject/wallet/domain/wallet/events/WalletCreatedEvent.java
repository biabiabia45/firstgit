package myproject.wallet.domain.wallet.events;

public class WalletCreatedEvent {
    private Long walletId;

    public WalletCreatedEvent(Long walletId) {
        this.walletId = walletId;
    }

    public Long getWalletId() {
        return walletId;
    }

    @Override
    public String toString() {
        return "WalletCreatedEvent{walletId=" + walletId + "}";
    }
}
