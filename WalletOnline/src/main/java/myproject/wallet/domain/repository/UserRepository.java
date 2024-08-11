package myproject.wallet.domain.repository;

import myproject.wallet.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    User save(User user);

    boolean existsById(Long id);

    void deleteById(Long id);
}

