package myproject.wallet.domain.valueobject;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ContactInfo {
    private String email;
    private String address;

    public ContactInfo() {

    }

    public ContactInfo (String email, String address) {
        // You can add validation here if needed
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.email = email;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return email.equals(that.email) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, address);
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
