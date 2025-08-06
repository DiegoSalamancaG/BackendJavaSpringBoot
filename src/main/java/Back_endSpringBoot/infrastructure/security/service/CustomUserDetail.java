package Back_endSpringBoot.infrastructure.security.service;

import Back_endSpringBoot.domain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Clase que adapta tu entidad User al modelo de seguridad de Spring Security.
 * Implementa UserDetails, proporcionando la información requerida para la autenticación.
 */
public record CustomUserDetail(User user) implements UserDetails {

    /**
     * Constructor que recibe un objeto User desde la base de datos.
     */
    public CustomUserDetail {
    }

    /**
     * Retorna las autoridades del usuario.
     * En este caso, se asigna ROLE_ADMIN o ROLE_USER según el campo "admin".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.getAdmin() ? "ROLE_ADMIN" : "ROLE_USER";
        return List.of(new SimpleGrantedAuthority(role));
    }

    /**
     * Retorna la contraseña del usuario (encriptada).
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Retorna el nombre de usuario.
     */
    @Override
    public String getUsername() {
        return user().getUsername();
    }

    /**
     * Determina si la cuenta está expirada. True = no expira.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Determina si la cuenta está bloqueada. True = no bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Determina si las credenciales están expiradas. True = no expiran.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Determina si la cuenta está habilitada.
     * Aquí usamos el campo `active` de la entidad User.
     */
    @Override
    public boolean isEnabled() {
        return user.getActive(); // Devuelve true si está habilitado
    }

    /**
     * Metodo adicional para acceder directamente al objeto User original.
     */
    @Override
    public User user() {
        return user;
    }
}
