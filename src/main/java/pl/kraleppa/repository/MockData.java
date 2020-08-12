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
        gameRepository.save(new Game(
                "Butterfield",
                69.99,
                Genre.SHOOTING,
                Console.PC,
                "Fajna giera ogólnie polecam",
                "https://rockmetalshop.pl/pol_pl_plakat-BATTLEFIELD-4-COVER-151108_1.jpg"
        ));

        gameRepository.save(new Game(
                "Assasyns krid",
                169.99,
                Genre.ACTION,
                Console.PC,
                "Średnio fajna giera :(",
                "https://a.allegroimg.com/s1024/0c6119/fa1a54524aba9f95890c12a59c49"
        ));

        gameRepository.save(new Game(
                "Butterfield",
                69.99,
                Genre.SHOOTING,
                Console.PC,
                "Fajna giera ogólnie polecam",
                "https://rockmetalshop.pl/pol_pl_plakat-BATTLEFIELD-4-COVER-151108_1.jpg"
        ));

        gameRepository.save(new Game(
                "Assasyns krid",
                169.99,
                Genre.ACTION,
                Console.PC,
                "Średnio fajna giera :(",
                "https://a.allegroimg.com/s1024/0c6119/fa1a54524aba9f95890c12a59c49"
        ));

        gameRepository.save(new Game(
                "Butterfield",
                69.99,
                Genre.SHOOTING,
                Console.PC,
                "Fajna giera ogólnie polecam",
                "https://rockmetalshop.pl/pol_pl_plakat-BATTLEFIELD-4-COVER-151108_1.jpg"
        ));

        gameRepository.save(new Game(
                "Assasyns krid",
                169.99,
                Genre.ACTION,
                Console.PC,
                "Średnio fajna giera :(",
                "https://a.allegroimg.com/s1024/0c6119/fa1a54524aba9f95890c12a59c49"
        ));

        gameRepository.save(new Game(
                "Butterfield",
                69.99,
                Genre.SHOOTING,
                Console.PC,
                "Fajna giera ogólnie polecam",
                "https://rockmetalshop.pl/pol_pl_plakat-BATTLEFIELD-4-COVER-151108_1.jpg"
        ));

        gameRepository.save(new Game(
                "Assasyns krid",
                169.99,
                Genre.ACTION,
                Console.PC,
                "Średnio fajna giera :(",
                "https://a.allegroimg.com/s1024/0c6119/fa1a54524aba9f95890c12a59c49"
        ));

        gameRepository.save(new Game(
                "Butterfield",
                69.99,
                Genre.SHOOTING,
                Console.PC,
                "Fajna giera ogólnie polecam",
                "https://rockmetalshop.pl/pol_pl_plakat-BATTLEFIELD-4-COVER-151108_1.jpg"
        ));

        gameRepository.save(new Game(
                "Assasyns krid",
                169.99,
                Genre.ACTION,
                Console.PC,
                "Średnio fajna giera :(",
                "https://a.allegroimg.com/s1024/0c6119/fa1a54524aba9f95890c12a59c49"
        ));

        gameRepository.save(new Game(
                "Butterfield",
                69.99,
                Genre.SHOOTING,
                Console.PC,
                "Fajna giera ogólnie polecam",
                "https://rockmetalshop.pl/pol_pl_plakat-BATTLEFIELD-4-COVER-151108_1.jpg"
        ));

        gameRepository.save(new Game(
                "Assasyns krid",
                169.99,
                Genre.ACTION,
                Console.PC,
                "Średnio fajna giera :(",
                "https://a.allegroimg.com/s1024/0c6119/fa1a54524aba9f95890c12a59c49"
        ));

        gameRepository.save(new Game(
                "Butterfield",
                69.99,
                Genre.SHOOTING,
                Console.PC,
                "Fajna giera ogólnie polecam",
                "https://rockmetalshop.pl/pol_pl_plakat-BATTLEFIELD-4-COVER-151108_1.jpg"
        ));

        gameRepository.save(new Game(
                "Assasyns krid",
                169.99,
                Genre.ACTION,
                Console.PC,
                "Średnio fajna giera :(",
                "https://a.allegroimg.com/s1024/0c6119/fa1a54524aba9f95890c12a59c49"
        ));
    }
}
