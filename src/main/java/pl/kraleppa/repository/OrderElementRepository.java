package pl.kraleppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kraleppa.model.entity.Order;
import pl.kraleppa.model.entity.OrderElement;

public interface OrderElementRepository extends JpaRepository<OrderElement, Long> {
}
