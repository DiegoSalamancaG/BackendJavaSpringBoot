package Back_endSpringBoot.application.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para representar la respuesta de un producto")
public class ProductResponseDTO {

    @Schema(description = "ID del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Katana Hattori Hanzo")
    private String name;

    @Schema(description = "Descripción detallada del producto", example = "Katana forjada a mano por Hattori Hanzo")
    private String description;

    @Schema(description = "URL de la imagen del producto", example = "https://misitio.com/imagenes/katana.jpg")
    private String image;

    @Schema(description = "Cantidad disponible en stock", example = "10")
    private Integer stock;

    @Schema(description = "Precio del producto", example = "150000")
    private Double price;

    @Schema(description = "Indica si el producto está activo", example = "true")
    private Boolean active;

    // Relación con categoría
    @Schema(description = "ID de la categoría", example = "2")
    private Long categoryId;

    @Schema(description = "Nombre de la categoría", example = "Espadas japonesas")
    private String categoryName;

    // Relación con colección
    @Schema(description = "ID de la colección", example = "3")
    private Long collectionId;

    @Schema(description = "Nombre de la colección", example = "Edición Clásica")
    private String collectionName;
}
