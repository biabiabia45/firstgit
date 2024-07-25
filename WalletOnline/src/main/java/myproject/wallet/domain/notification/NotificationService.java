package myproject.wallet.domain.notification;

import myproject.wallet.domain.notification.exception.NotificationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications(UUID userId, UUID walletId) {
        // 根据 userId 和 walletId 查询所有通知
        return notificationRepository.findByUserIdAndWalletId(userId, walletId);
    }

    public Optional<Notification> getNotificationById(UUID userId, UUID walletId, UUID notificationId) {
        // 根据 userId, walletId 和 notificationId 查询通知
        return notificationRepository.findByUserIdAndWalletIdAndId(userId, walletId, notificationId);
    }

    public Notification createNotification(UUID userId, UUID walletId, Notification notification) {
        // 设置 userId 和 walletId 到 notification 对象
        notification.setUserId(userId);
        notification.setWalletId(walletId);
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(UUID userId, UUID walletId, UUID notificationId, Notification notification) {
        if (notificationRepository.existsByUserIdAndWalletIdAndId(userId, walletId, notificationId)) {
            notification.setId(notificationId);
            return notificationRepository.save(notification);
        } else {
            throw new NotificationNotFoundException("Notification not found");
        }
    }

    public void deleteNotification(UUID userId, UUID walletId, UUID notificationId) {
        if (notificationRepository.existsByUserIdAndWalletIdAndId(userId, walletId, notificationId)) {
            notificationRepository.deleteById(notificationId);
        } else {
            throw new NotificationNotFoundException("Notification not found");
        }
    }
}
