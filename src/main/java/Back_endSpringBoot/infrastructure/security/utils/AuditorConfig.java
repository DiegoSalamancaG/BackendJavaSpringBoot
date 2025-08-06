package Back_endSpringBoot.infrastructure.security.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // Valor genérico para pruebas sin autenticación
        return () -> Optional.of("System");
    }
}
