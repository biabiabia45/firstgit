package myproject.wallet.domain.user.entity;

import myproject.wallet.domain.valueobject.ContactInfo;

public class User {

    private Long id;
    private String username;
    private String password;
    private ContactInfo contactInfo;

//    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

//    public void setPassword(String password) {
//        this.password = encryptPassword(password);
//    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

//    public boolean checkPassword(String rawPassword) {
//        return passwordEncoder.matches(rawPassword, this.password);
//    }
//
//    private String encryptPassword(String rawPassword) {
//        return passwordEncoder.encode(rawPassword);
//    }

//    public void updatePassword(String oldPassword, String newPassword) {
//        if (!checkPassword(oldPassword)) {
//            throw new IllegalArgumentException("Old password is incorrect.");
//        }
//        this.password = encryptPassword(newPassword);
//    }
}
