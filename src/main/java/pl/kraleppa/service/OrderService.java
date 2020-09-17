package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.model.entity.Order;
import pl.kraleppa.model.request.Basket;
import pl.kraleppa.model.request.OrderOptions;
import pl.kraleppa.repository.GameRepository;
import pl.kraleppa.repository.MyUserRepository;
import pl.kraleppa.repository.OrderElementRepository;
import pl.kraleppa.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final GameRepository gameRepository;
    private final MyUserRepository myUserRepository;
    private final OrderElementRepository orderElementRepository;

    public Order createOrder(String username, OrderOptions orderOptions){
        MyUser myUser = myUserRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        Order order = new Order(new Basket(myUser.getBasket()), orderOptions);
        myUser.addOrder(order);
        orderElementRepository.saveAll(order.getOrderElements());
        Order res = orderRepository.save(order);
        myUserRepository.save(myUser);
        return res;
    }

    public List<Order> getOrders(String username){
        MyUser myUser = myUserRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        return orderRepository.findAllByUser(myUser);
    }
}
