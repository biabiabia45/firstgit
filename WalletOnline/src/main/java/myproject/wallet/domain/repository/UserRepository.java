package myproject.wallet.domain.repository;

import myproject.wallet.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean existsById(Long id);


    boolean existsByUserName(String username);

    void deleteByUsername(String username);

    void changePassword(String username, String newPassword);
}

