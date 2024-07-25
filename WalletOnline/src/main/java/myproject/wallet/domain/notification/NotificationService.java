package myproject.wallet.domain.notification;

import myproject.wallet.domain.notification.events.NotificationCreatedEvent;
import myproject.wallet.domain.notification.events.NotificationDeletedEvent;
import myproject.wallet.domain.notification.events.NotificationUpdatedEvent;
import myproject.wallet.domain.notification.kafka.NotificationEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final NotificationEventProducer notificationEventProducer;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, ApplicationEventPublisher eventPublisher, NotificationEventProducer notificationEventProducer) {
        this.notificationRepository = notificationRepository;
        this.eventPublisher = eventPublisher;
        this.notificationEventProducer = notificationEventProducer;
    }

    public Optional<Notification> getNotificationById(UUID id) {
        return notificationRepository.findById(id);
    }

    public List<Notification> getNotificationsByUserId(UUID userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
        NotificationCreatedEvent event = new NotificationCreatedEvent(notification.getId());
        eventPublisher.publishEvent(event);
        notificationEventProducer.sendNotificationCreatedEvent(event.toString()); // 将事件转换为字符串并发送到 Kafka
    }

    public void updateNotification(Notification notification) {
        notificationRepository.save(notification);
        NotificationUpdatedEvent event = new NotificationUpdatedEvent(notification.getId());
        eventPublisher.publishEvent(event);
        notificationEventProducer.sendNotificationUpdatedEvent(event.toString()); // 将事件转换为字符串并发送到 Kafka
    }

    public void deleteNotification(UUID id) {
        notificationRepository.deleteById(id);
        NotificationDeletedEvent event = new NotificationDeletedEvent(id);
        eventPublisher.publishEvent(event);
        notificationEventProducer.sendNotificationDeletedEvent(event.toString()); // 将事件转换为字符串并发送到 Kafka
    }
}
