package pl.kraleppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kraleppa.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
