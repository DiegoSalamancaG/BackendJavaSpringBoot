package Back_endSpringBoot.infrastructure.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para recibir las credenciales del usuario al hacer login.
 * Contiene el nombre de usuario y la contraseña.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Solicitud de autenticación del usuario(login)")
public class AuthRequest {

    @Schema(description = "Nombre de usuario registrado", example = "diego123")
    private String username;

    @Schema(description = "Contraseña del usuario", example = "12345abc")
    private String password;
}
