package pl.kraleppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.model.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(MyUser user);
}
