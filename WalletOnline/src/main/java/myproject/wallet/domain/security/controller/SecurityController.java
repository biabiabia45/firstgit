package myproject.wallet.web;

import myproject.wallet.domain.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        securityService.registerUser(username, password, role);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticateUser(@RequestParam String username, @RequestParam String password) {
        boolean authenticated = securityService.authenticate(username, password);
        return ResponseEntity.ok(authenticated);
    }
}
