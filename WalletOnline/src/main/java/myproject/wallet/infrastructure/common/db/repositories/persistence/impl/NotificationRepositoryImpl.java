package myproject.wallet.infrastructure.common.db.repositories.persistence.impl;

import myproject.wallet.domain.entities.Notification;
import myproject.wallet.domain.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NotificationRepositoryImpl implements NotificationRepository {
    @Override
    public List<Notification> findByUserIdAndWalletId(UUID userId, UUID walletId) {
        return null;
    }

    @Override
    public Optional<Notification> findByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id) {
        return false;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
