package myproject.wallet.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUserIdAndWalletId(UUID userId, UUID walletId);

    Optional<Notification> findByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    boolean existsByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    void deleteById(UUID id);
}
