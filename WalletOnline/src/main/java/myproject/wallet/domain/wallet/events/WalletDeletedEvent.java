package myproject.wallet.domain.wallet.events;

public class WalletDeletedEvent {
    private Long walletId;

    public WalletDeletedEvent(Long walletId) {
        this.walletId = walletId;
    }

    public Long getWalletId() {
        return walletId;
    }

    @Override
    public String toString() {
        return "WalletDeletedEvent{walletId=" + walletId + "}";
    }
}
