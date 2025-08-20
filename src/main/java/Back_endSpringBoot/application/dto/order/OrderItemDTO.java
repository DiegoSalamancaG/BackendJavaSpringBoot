package Back_endSpringBoot.application.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    @NotNull(message = "Id del producto es obligatorio")
    private Long productId;

    @Min(value = 1, message = "Cantidad debe ser de al menos 1")
    private int quantity;
}
