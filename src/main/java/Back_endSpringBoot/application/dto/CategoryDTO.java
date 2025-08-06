package Back_endSpringBoot.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    @Schema(description = "Nombre de la Categoria")
    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Schema(description = "Descripción de la Categoria")
    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    private String description;
}
