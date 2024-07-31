package myproject.wallet.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import myproject.wallet.domain.entities.valueobject.Account;
import myproject.wallet.domain.entities.valueobject.UserProfile;

import java.util.Objects;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UserProfile profile;
    private Account account;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return id;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public Account getAccount() {
        return account;
    }

    // 注册用户
    public void register(String username, String password, String email) {
        this.profile = new UserProfile(username, email);
        this.account = new Account(password);
        // 触发领域事件
        DomainEventPublisher.publish(new UserRegistered(this.userId));
    }

    public void updateProfile(String username, String email) {
        this.profile.update(username, email);
        DomainEventPublisher.publish(new UserProfileUpdated(this.userId));
    }

    // 验证用户密码
    public boolean authenticate(String password) {
        return this.account.verifyPassword(password);
    }

    // 重置用户密码
    public void resetPassword(String newPassword) {
        this.account.changePassword(newPassword);
        DomainEventPublisher.publish(new UserPasswordChanged(this.userId));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

}

