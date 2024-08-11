package myproject.wallet.infrastructure.config;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class GenerateKey {
    public static void main(String[] args) {
        try {
            // 创建一个 KeyGenerator 实例，并初始化为 256 位
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256); // 为 HMAC-SHA-256 生成一个 256 位的密钥
            SecretKey secretKey = keyGenerator.generateKey();

            // 将密钥编码为 Base64 字符串
            String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("Generated Key (Base64): " + base64Key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

