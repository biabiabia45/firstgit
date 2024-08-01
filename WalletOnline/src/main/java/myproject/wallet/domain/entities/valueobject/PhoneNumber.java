package myproject.wallet.domain.entities.valueobject;

import java.util.Objects;

public class PhoneNumber {
    private final String value;

    public PhoneNumber(String value) {
        if (!value.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid phone number format.");
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
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
