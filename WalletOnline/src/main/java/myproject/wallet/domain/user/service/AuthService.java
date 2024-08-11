package myproject.wallet.domain.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import myproject.wallet.domain.exceptions.UserNotFoundException;
import myproject.wallet.domain.user.entity.User;
import myproject.wallet.domain.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String credential, String password) {
        Optional<User> userOpt;
        if (isEmail(credential)) {
            userOpt = userRepository.findByEmail(credential);
        } else {
            userOpt = userRepository.findByUsername(credential);
        }

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return generateToken(user);
            }
        }
        throw new UserNotFoundException("Invalid username, email or password.");
    }

    private boolean isEmail(String credential) {
        return EmailValidator.getInstance().isValid(credential);
    }



    private String generateToken(User user) {
        // 使用 HashMap 来创建 Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());  // 将 subject 设置为 username
        claims.put("userId", user.getId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpiration);

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
