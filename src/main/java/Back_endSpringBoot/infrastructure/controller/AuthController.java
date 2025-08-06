package Back_endSpringBoot.infrastructure.controller;

import Back_endSpringBoot.application.dto.UserDTO;
import Back_endSpringBoot.application.mapper.UserMapper;
import Back_endSpringBoot.application.service.AuthService;
import Back_endSpringBoot.domain.repository.UserRepository;
import Back_endSpringBoot.infrastructure.security.dto.AuthRequest;
import Back_endSpringBoot.infrastructure.security.dto.AuthResponse;
import Back_endSpringBoot.infrastructure.security.jwt.JwtUtil;
import Back_endSpringBoot.infrastructure.security.service.CustomUserDetail;
import Back_endSpringBoot.infrastructure.security.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO request){
        try{
            String message = authService.register(request);
            return ResponseEntity.status(201).body(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
