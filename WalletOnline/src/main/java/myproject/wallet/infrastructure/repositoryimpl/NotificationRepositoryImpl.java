package myproject.wallet.infrastructure.repositoryimpl;

import myproject.wallet.domain.mapper.NotificationMapper;
import myproject.wallet.domain.notification.entity.Notification;
import myproject.wallet.domain.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationRepositoryImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<Notification> findByUserIdAndWalletId(UUID userId, UUID walletId) {
        return notificationMapper.findByUserIdAndWalletId(userId, walletId);
    }

    @Override
    public Optional<Notification> findByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id) {
        return notificationMapper.findByUserIdAndWalletIdAndId(userId, walletId, id);
    }

    @Override
    public boolean existsByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id) {
        return notificationMapper.existsByUserIdAndWalletIdAndId(userId, walletId, id);
    }

    @Override
    public void deleteById(UUID id) {
        notificationMapper.deleteById(id);
    }

    @Override
    public Notification save(Notification notification) {
        notificationMapper.save(notification);
        return notification;
    }
}
