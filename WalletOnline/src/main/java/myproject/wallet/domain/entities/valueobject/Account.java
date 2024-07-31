package myproject.wallet.domain.entities.valueobject;

import java.util.Objects;

public class Account {
    private String password;

    public Account(String password) {
        this.password = hashPassword(password);
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(hashPassword(password));
    }

    public void changePassword(String newPassword) {
        this.password = hashPassword(newPassword);
    }

    private String hashPassword(String password) {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
