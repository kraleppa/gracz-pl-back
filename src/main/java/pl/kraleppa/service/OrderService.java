package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.dictionary.OrderState;
import pl.kraleppa.model.entity.Game;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.model.entity.Order;
import pl.kraleppa.model.request.Basket;
import pl.kraleppa.model.request.Credentials;
import pl.kraleppa.model.request.OrderOptions;
import pl.kraleppa.repository.GameRepository;
import pl.kraleppa.repository.MyUserRepository;
import pl.kraleppa.repository.OrderElementRepository;
import pl.kraleppa.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        List<Game> gamesList = myUser.getBasket();
        myUser.getBasket().forEach(myUser::deleteGameFromBasket);
        gamesList.forEach(gameRepository::save);
        myUserRepository.save(myUser);
        return res;
    }

    public List<Order> getOrders(String username){
        MyUser myUser = myUserRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        return orderRepository.findByUserOrderByCreationDateDesc(myUser);
    }

    public List<Order> getOrdersInProgress(Optional<Boolean> inProgress){
        if (inProgress.isEmpty() || !inProgress.get()){
            return orderRepository.findAll();
        }
        return orderRepository.findAllByOrderStateIsNot(OrderState.SENT);
    }

    public Order changeOrderState(OrderState orderState, Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setOrderState(orderState);
        return orderRepository.save(order);
    }

    public Credentials getCredentials(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return new Credentials(order.getUser());
    }
}
