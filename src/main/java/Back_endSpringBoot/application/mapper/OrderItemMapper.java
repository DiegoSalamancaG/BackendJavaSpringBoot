package Back_endSpringBoot.application.mapper;

import Back_endSpringBoot.application.dto.order.OrderItemDTO;
import Back_endSpringBoot.application.dto.order.OrderItemResponseDTO;
import Back_endSpringBoot.domain.model.order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem toEntity(OrderItemDTO dto);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "price")
    OrderItemResponseDTO toResponseDTO(OrderItem orderItem);
}
