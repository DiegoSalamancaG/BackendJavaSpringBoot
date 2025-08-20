package Back_endSpringBoot.domain.repository;

import Back_endSpringBoot.domain.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
