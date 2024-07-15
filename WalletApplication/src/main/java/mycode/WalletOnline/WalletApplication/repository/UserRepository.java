package mycode.WalletOnline.WalletApplication.repository;
import mycode.WalletOnline.WalletApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByEmail(String email);
}
