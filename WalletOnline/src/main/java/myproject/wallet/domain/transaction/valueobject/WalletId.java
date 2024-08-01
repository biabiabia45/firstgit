package myproject.wallet.domain.transaction.valueobject;

import java.util.Objects;
import java.util.UUID;

public class WalletId {
    private final UUID value;

    public WalletId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
    public UUID getId() {
        return value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletId walletId = (WalletId) o;
        return value.equals(walletId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "WalletId{" +
                "value=" + value +
                '}';
    }
}
