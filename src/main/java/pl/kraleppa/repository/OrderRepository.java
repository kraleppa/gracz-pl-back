package pl.kraleppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kraleppa.model.dictionary.OrderState;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.model.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByCreationDateDesc(MyUser user);
    List<Order> findAllByOrderStateIsNot(OrderState orderState);
}
