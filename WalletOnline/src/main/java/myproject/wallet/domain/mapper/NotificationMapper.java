package myproject.wallet.domain.mapper;

import myproject.wallet.domain.notification.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface NotificationMapper {

    List<Notification> findByUserIdAndWalletId(UUID userId, UUID walletId);

    Optional<Notification> findByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    boolean existsByUserIdAndWalletIdAndId(UUID userId, UUID walletId, UUID id);

    void deleteById(UUID id);

    void save(Notification notification);
}
