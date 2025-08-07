package Back_endSpringBoot.application.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para crear o actualizar productos en el sistema")
public class ProductDTO {

    @Schema(description = "ID del producto (solo en respuestas)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre del producto", example = "Katana Hattori Hanzo", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Schema(description = "Descripción del producto", example = "Katana forjada a mano por Hattori Hanzo con hoja de acero de alta calidad", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    private String description;

    @Schema(description = "URL de la imagen del producto", example = "https://misitio.com/imagenes/katana.jpg")
    //@Pattern(regexp = "^(https?|ftp)://.*$", message = "La URL de la imagen debe ser válida")
    private String image;

    @Schema(description = "Stock disponible del producto", example = "20", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad no puede ser menor que 1")
    private Integer stock;

    @Schema(description = "Precio del producto", example = "150000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "1.0", message = "El precio no puede ser menor que 1")
    private Double price;

    @Schema(description = "Indica si el producto está activo", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean active = true;

    @Schema(description = "ID de la categoría asociada", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La categoría es obligatoria")
    private Long categoryId;

    @Schema(description = "ID de la colección asociada (opcional)", example = "3")
    private Long collectionId;

    @Schema(description = "Nombre de la categoría (solo lectura)", example = "Armas", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoryName;

    @Schema(description = "Nombre de la colección (solo lectura)", example = "Edición Clásica", accessMode = Schema.AccessMode.READ_ONLY)
    private String collectionName;

}
