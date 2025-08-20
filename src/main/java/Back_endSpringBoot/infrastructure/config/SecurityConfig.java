package Back_endSpringBoot.infrastructure.config;

import Back_endSpringBoot.domain.repository.UserRepository;
import Back_endSpringBoot.infrastructure.security.jwt.JwtAuthFilter;
import Back_endSpringBoot.infrastructure.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Bean para codificar contraseñas con BCrypt.
     * Es usado automáticamente por Spring Security en los procesos de autenticación.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el filtro de seguridad principal para la aplicación.
     * - Desactiva CSRF (ya que es una API REST).
     * - Establece política de sesión como STATELESS (sin sesiones).
     * - Permite acceso libre a rutas bajo /api/v1/auth/**
     * - Requiere autenticación para todas las demás rutas.
     * - Agrega el filtro JWT personalizado antes del filtro por defecto de autenticación.
     *
     * @param http instancia de HttpSecurity proporcionada por Spring
     * //@param jwtFilter filtro personalizado que valida el JWT en las peticiones
     * @return SecurityFilterChain configurado
     * @throws Exception si ocurre un error de configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Regla #1: Permite explícitamente la creación de usuarios para CUALQUIERA.
                        .requestMatchers("/api/v1/auth/**").permitAll()// Permitir acceso sin autenticación a rutas públicas
                        .requestMatchers(HttpMethod.GET,"/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/orders/**").authenticated()

                        // Regla #2: Requiere el rol 'ADMIN' para el resto de los endpoints de usuarios.
                        // Esto protege GET, PUT, DELETE en /api/v1/users/**
                        .requestMatchers("/api/v1/users/**").hasRole("ADMIN")

                        // Regla #3 (Opcional pero recomendado): Permite el acceso a la UI de Swagger.
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Regla #4: Cualquier otra petición no definida arriba requiere autenticación.
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Bean que expone el AuthenticationManager para usar en el proceso de login.
     *
     * @param authConfig configuración de autenticación de Spring
     * @return AuthenticationManager configurado
     * @throws Exception si ocurre un error
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Bean de servicio personalizado para cargar los detalles del usuario.
     * Se usa para buscar usuarios por email o username desde la base de datos.
     *
     * @param userRepository Repositorio de usuarios inyectado por Spring
     * @return instancia del servicio de detalles de usuario
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService (UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }
}
