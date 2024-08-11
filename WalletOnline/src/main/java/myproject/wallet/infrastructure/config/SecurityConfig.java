package myproject.wallet.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF 保护
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/wallet-online/login").permitAll() // 允许访问登录 API
                                .requestMatchers("/wallet-online/users/**").permitAll() // 允许访问用户相关的 API
                                .anyRequest().authenticated() // 其他请求需要认证
                )
                .formLogin(AbstractHttpConfigurer::disable);// 如果不需要表单登录，可以禁用

        return http.build();
    }

}
