package Back_endSpringBoot.application.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Objeto de transferencia de datos para operaciones con usuarios")
public class UserDTO {

    @Schema(description = "Nombre del usuario", example = "Diego Salamanca", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @Schema(description = "Apellido del usuario", example = "Diego Salamanca", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @Schema(description = "Nombre de usuario único para iniciar sesión", example = "diego123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "Correo electrónico válido del usuario", example = "diego@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Contraseña del usuario (mínimo 6 caracteres)", example = "MiPassSegura123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Define si el usuario tiene rol de administrador", example = "false")
    private Boolean admin;

    @Schema(description = "Indica si la cuenta del usuario está activa", example = "true")
    private Boolean active;
}
