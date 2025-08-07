package Back_endSpringBoot.application.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO de respuesta para datos públicos del usuario")
public class UserResponseDTO {

    @Schema(description = "ID único del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre del usuario", example = "Diego Salamanca")
    private String firstName;

    @Schema(description = "Apellido del usuario", example = "Salamanca")
    private String lastName;

    @Schema(description = "Nombre completo del usuario", example = "Diego Salamanca")
    private String fullName;

    @Schema(description = "Nombre de usuario", example = "diego123")
    private String username;

    @Schema(description = "Correo electrónico del usuario", example = "diego@example.com")
    private String email;

    @Schema(description = "Indica si el usuario tiene permisos de administrador", example = "false")
    private Boolean admin;

    @Schema(description = "Indica si el usuario está activo", example = "true")
    private Boolean active;
}
