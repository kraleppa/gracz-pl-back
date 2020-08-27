package pl.kraleppa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kraleppa.model.Game;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.model.dictionary.Genre;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MockData {
    private final GameRepository gameRepository;

    @PostConstruct
    public void gameInit(){

        for (int i = 0; i < 30; i++){
            gameRepository.save(new Game(
                    "Hejlo"+i,
                    69.99,
                    Genre.SHOOTING,
                    Console.XBOX_ONE,
                    "Fajna giera ogólnie polecam",
                    "https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg"
            ));

            gameRepository.save(new Game(
                    "The last of us"+i,
                    420.99,
                    Genre.ACTION,
                    Console.PLAYSTATION_4,
                    "Średnio fajna giera :(",
                    "https://a.allegroimg.com/s512/037b18/ced7818d4a9c914abafbd856762a/THE-LAST-OF-US-PART-2-II-PS4-PLAYSTATION-PL-DUBB"
            ));

            gameRepository.save(new Game(
                    "Mario Kart"+i,
                    21.37,
                    Genre.RACING,
                    Console.NINTENDO_SWITCH,
                    "Średnio fajna giera :(",
                    "https://i0.wp.com/www.semperludo.com/wp-content/uploads/2017/04/Mario-Kart-8-Deluxe-Switch-cover.jpg?fit=456%2C738&ssl=1"
            ));

            gameRepository.save(new Game(
                    "Forza"+i,
                    19.99,
                    Genre.RACING,
                    Console.XBOX_ONE,
                    "Fajna giera ogólnie polecam",
                    "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
            ));

            gameRepository.save(new Game(
                    "Gran Turismo"+i,
                    12.33,
                    Genre.RACING,
                    Console.PLAYSTATION_4,
                    "Średnio fajna giera :(",
                    "https://ecsmedia.pl/c/gran-turismo-sport-ps-hits-b-iext55519998.jpg"
            ));
        }
    }
}
