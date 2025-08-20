package Back_endSpringBoot.domain.service;

import Back_endSpringBoot.application.dto.order.OrderDTO;
import Back_endSpringBoot.application.dto.order.OrderResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderResponseDTO> getAllOrders();
    Optional<OrderResponseDTO> getOrderById(Long id);
    Optional<OrderResponseDTO> createOrder(OrderDTO dto);
    Boolean deleteOrder(Long id);

}
