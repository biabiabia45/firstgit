package myproject.wallet.domain.repository;

import myproject.wallet.domain.notification.entity.Notification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {

    List<Notification> findByUserIdAndWalletId(UUID userId, UUID walletId);

    Optional<Notification> findByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    boolean existsByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    void deleteById(UUID id);

    Notification save(Notification notification);
}
