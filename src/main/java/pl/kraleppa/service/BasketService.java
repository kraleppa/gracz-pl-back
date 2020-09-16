package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.entity.Game;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.model.request.Basket;
import pl.kraleppa.repository.GameRepository;
import pl.kraleppa.repository.MyUserRepository;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final GameRepository gameRepository;
    private final MyUserRepository myUserRepository;

    public Basket addGameToBasket(String username, Long gameId){
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
        MyUser myUser = myUserRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        myUser.insertGameToBasket(game);
        myUserRepository.save(myUser);
        gameRepository.save(game);

        return new Basket(myUser.getBasket());
    }

    public Basket getBasket(String username){
        MyUser myUser = myUserRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new Basket(myUser.getBasket());
    }

    public Basket deleteGameFromBasket(String username, Long gameId){
        MyUser myUser = myUserRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        myUser.deleteGameFromBasket(game);

        myUserRepository.save(myUser);
        gameRepository.save(game);

        return new Basket(myUser.getBasket());
    }
}
