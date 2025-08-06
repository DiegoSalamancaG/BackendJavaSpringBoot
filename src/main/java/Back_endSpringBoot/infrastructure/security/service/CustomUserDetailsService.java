package Back_endSpringBoot.infrastructure.security.service;

import Back_endSpringBoot.domain.model.User;
import Back_endSpringBoot.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que implementa UserDetailsService para integrar la autenticación con Spring Security.
 * Busca al usuario por su nombre de usuario y lo transforma en un CustomUserDetail que Spring pueda usar.
 */
@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            User user = userRepository.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado: "+username));
            return  new CustomUserDetail(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar usuario: "+e.getMessage(), e);
        }
    }

}
