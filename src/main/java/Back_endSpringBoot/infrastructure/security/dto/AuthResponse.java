package Back_endSpringBoot.infrastructure.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para devolver el token JWT al cliente
 * después de una autenticación exitosa.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta con el token JWT luego de una autenticación exitosa")
public class AuthResponse {

    @Schema(description = "Token JWT generado para autenticación", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}
