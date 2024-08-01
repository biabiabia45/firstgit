package myproject.wallet.domain.repository;

import myproject.wallet.domain.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findById(UUID id);

    List<User> findAll();

    User save(User user);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
