package myproject.wallet.domain.repository;

import myproject.wallet.domain.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepository {

    List<Notification> findByUserIdAndWalletId(UUID userId, UUID walletId);

    Optional<Notification> findByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    boolean existsByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    void deleteById(UUID id);
}
