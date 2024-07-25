package myproject.wallet.domain.security;

import java.util.Optional;
import java.util.UUID;

public interface SecurityUserRepository {
    Optional<SecurityUser> findById(UUID id);
    Optional<SecurityUser> findByUsername(String username);
    void save(SecurityUser user);
}
