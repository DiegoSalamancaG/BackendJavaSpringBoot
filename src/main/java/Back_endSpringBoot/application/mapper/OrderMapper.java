package Back_endSpringBoot.application.mapper;

import Back_endSpringBoot.application.dto.order.OrderDTO;
import Back_endSpringBoot.application.dto.order.OrderResponseDTO;
import Back_endSpringBoot.domain.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    Order toEntity(OrderDTO dto);

    //@Mapping(source = "user.id", target = "userId")
    OrderResponseDTO toResponseDTO(Order order);
}
