package myproject.wallet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("myproject.wallet.domain.mapper")
public class WalletOnline {
    public static void main(String[] args) {
        SpringApplication.run(WalletOnline.class, args);
    }
}