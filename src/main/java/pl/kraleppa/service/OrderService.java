package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.model.entity.Order;
import pl.kraleppa.model.request.Basket;
import pl.kraleppa.repository.GameRepository;
import pl.kraleppa.repository.MyUserRepository;
import pl.kraleppa.repository.OrderRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final GameRepository gameRepository;
    private final MyUserRepository myUserRepository;

    public void createOrder(String username){
        MyUser myUser = myUserRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        Order order = new Order(new Basket(myUser.getBasket()));
        myUser.addOrder(order);

        orderRepository.save(order);
        myUserRepository.save(myUser);
        order.getOrderedGames().forEach(gameRepository::save);
    }
}
