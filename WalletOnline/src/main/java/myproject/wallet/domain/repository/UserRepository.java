package myproject.wallet.domain.repository;

import myproject.wallet.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID userId);
    Optional<User> findByUsername(String username);
    void delete(User user);

    boolean existsById(UUID id);

    List<User> findAll();

    void deleteById(UUID id);
}
