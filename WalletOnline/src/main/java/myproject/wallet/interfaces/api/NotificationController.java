package myproject.wallet.interfaces.api;

import myproject.wallet.domain.entities.Notification;
import myproject.wallet.domain.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/wallet-online/users/{userId}/wallets/{walletId}/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications(
            @PathVariable UUID userId,
            @PathVariable UUID walletId) {
        List<Notification> notifications = notificationService.getAllNotifications(userId, walletId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(
            @PathVariable UUID userId,
            @PathVariable UUID walletId,
            @PathVariable UUID notificationId) {
        Optional<Notification> notification = notificationService.getNotificationById(userId, walletId, notificationId);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @PathVariable UUID userId,
            @PathVariable UUID walletId,
            @RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(userId, walletId, notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity<Notification> updateNotification(
            @PathVariable UUID userId,
            @PathVariable UUID walletId,
            @PathVariable UUID notificationId,
            @RequestBody Notification notification) {
        notification.setId(notificationId);
        Notification updatedNotification = notificationService.updateNotification(userId, walletId, notificationId, notification);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable UUID userId,
            @PathVariable UUID walletId,
            @PathVariable UUID notificationId) {
        notificationService.deleteNotification(userId, walletId, notificationId);
        return ResponseEntity.noContent().build();
    }
}
