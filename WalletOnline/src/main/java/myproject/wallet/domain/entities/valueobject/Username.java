package myproject.wallet.domain.entities.valueobject;

import java.util.Objects;

public class Username {
    private final String value;

    public Username(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username that = (Username) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
