package Back_endSpringBoot.domain.repository;

import Back_endSpringBoot.domain.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
