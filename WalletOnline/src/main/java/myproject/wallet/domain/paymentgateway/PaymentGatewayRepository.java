package myproject.wallet.domain.paymentgateway;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, UUID> {
}
