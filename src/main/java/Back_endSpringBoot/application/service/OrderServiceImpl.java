package Back_endSpringBoot.application.service;

import Back_endSpringBoot.application.dto.order.OrderDTO;
import Back_endSpringBoot.application.dto.order.OrderResponseDTO;
import Back_endSpringBoot.application.mapper.OrderMapper;
import Back_endSpringBoot.domain.model.Product;
import Back_endSpringBoot.domain.model.User;
import Back_endSpringBoot.domain.model.order.Order;
import Back_endSpringBoot.domain.model.order.OrderItem;
import Back_endSpringBoot.domain.model.order.OrderStatus;
import Back_endSpringBoot.domain.repository.OrderRepository;
import Back_endSpringBoot.domain.repository.ProductRepository;
import Back_endSpringBoot.domain.repository.UserRepository;
import Back_endSpringBoot.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderResponseDTO> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public Optional<OrderResponseDTO> createOrder(OrderDTO dto) {
        // 1. Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User userAuth = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

        // 2. Crear la entidad Order y asignarle el usuario
        Order order = new Order();
        order.setUser(userAuth);
        order.setStatus(OrderStatus.PENDING);
        order.setItems(new ArrayList<>());

        // 3. Construir OrderItem y calcular subtotales por item
        List<OrderItem> orderItems = dto.getItems().stream()
                .map(itemDto->{
                    Product product = productRepository.findById(itemDto.getProductId())
                            .orElseThrow(()-> new RuntimeException("Producto no encontrado: "+itemDto.getProductId()));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setPrice(product.getPrice());
                    orderItem.setOrder(order);

                    return orderItem;
                }).collect(Collectors.toList());

        // 4. Asignar la lista de OrderItems a la Order
        order.setItems(orderItems);

        // 5. Calcular el total de la orden
        BigDecimal total = orderItems.stream()
                .map(item->item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        order.setTotal(total);

        // 6. Guardar la orden y retornar el DTO de respuesta
        Order orderSaved = orderRepository.save(order);
        OrderResponseDTO responseDTO = orderMapper.toResponseDTO(orderSaved);

        // 7. Calcular subtotal en cada item del DTO
        responseDTO.getItems().forEach(item -> {
            item.setSubtotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        });

        return Optional.of(responseDTO);
    }

    @Override
    public Boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }
}
