package Back_endSpringBoot.application.service;

import Back_endSpringBoot.application.dto.user.UserDTO;
import Back_endSpringBoot.application.mapper.UserMapper;
import Back_endSpringBoot.domain.model.User;
import Back_endSpringBoot.domain.repository.UserRepository;
import Back_endSpringBoot.infrastructure.security.dto.AuthRequest;
import Back_endSpringBoot.infrastructure.security.dto.AuthResponse;
import Back_endSpringBoot.infrastructure.security.jwt.JwtUtil;
import Back_endSpringBoot.infrastructure.security.service.CustomUserDetail;
import Back_endSpringBoot.infrastructure.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;


    public AuthResponse login(AuthRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        }catch (Exception e){
            throw new RuntimeException("Credenciales inválidas");
        }

        CustomUserDetail userDetail = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetail.getUsername());
        return new AuthResponse(token);
    }

    public String register(UserDTO request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Usuario ya existe!");
        }

        // Mapea el DTO a User
        User newUser = userMapper.toUser(request);

        // Revisamos campos importantes
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setAdmin(false);
        newUser.setActive(true);

        return "Usuario registrado correctamente";
    }

}
