package myproject.wallet.domain.user.entity;

import jakarta.persistence.Column;
import myproject.wallet.domain.valueobject.ContactInfo;

public class User {

    private Long id;
    private String username;
    private String password;
    private ContactInfo contactInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String updatePassword(String newPassword) {
        return this.password = newPassword;
    }
}
