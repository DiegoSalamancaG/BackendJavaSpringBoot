package Back_endSpringBoot.application.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotEmpty(message = "La orden debe contener al menos un item")
    private List<OrderItemDTO> items;
}
