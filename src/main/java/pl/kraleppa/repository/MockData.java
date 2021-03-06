package pl.kraleppa.repository;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import pl.kraleppa.model.dictionary.Role;
import pl.kraleppa.model.entity.Game;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.model.dictionary.Genre;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.model.request.OrderOptions;
import pl.kraleppa.service.BasketService;
import pl.kraleppa.service.OrderService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MockData {
    private final GameRepository gameRepository;
    private final MyUserRepository myUserRepository;
    private final BasketService basketService;
    private final OrderService orderService;

    @PostConstruct
    public void gameInit(){

        myUserRepository.save(MyUser.builder()
                .username("kraleppa")
                .password("$2a$10$gzMqpcRE7IpJdLR8/mhCHupgmRu8QWKi/h6g1ANkfpdV8SbkVHEnu")
                .email("email@costam.pl")
                .name("Krzysztof")
                .surname("Nalepa")
                .role(Role.ROLE_ADMIN)
                .address("Krakowska 1")
                .city("Bibice")
                .zip("32-087")
                .build()
        );

        gameRepository.save(Game.builder()
                .name("Halo")
                .price(199.99)
                .genre(Genre.SHOOTING)
                .console(Console.XBOX_ONE)
                .description("Świetna gra!")
                .imageUrl("https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg")
                .build()
        );

        gameRepository.save(Game.builder()
                .name("The last of us II")
                .price(120.99)
                .genre(Genre.ACTION)
                .console(Console.PLAYSTATION_4)
                .description("Bardzo dobra gra!")
                .imageUrl("https://a.allegroimg.com/s512/037b18/ced7818d4a9c914abafbd856762a/THE-LAST-OF-US-PART-2-II-PS4-PLAYSTATION-PL-DUBB")
                .build()
        );

        gameRepository.save(Game.builder()
                .name("Mario Kart 8")
                .price(43.33)
                .genre(Genre.RACING)
                .console(Console.NINTENDO_SWITCH)
                .description("Przeciętna gra!")
                .imageUrl("https://i0.wp.com/www.semperludo.com/wp-content/uploads/2017/04/Mario-Kart-8-Deluxe-Switch-cover.jpg?fit=456%2C738&ssl=1")
                .build()
        );

        gameRepository.save(Game.builder()
                .name("Forza 6")
                .price(19.99)
                .genre(Genre.RACING)
                .console(Console.XBOX_ONE)
                .description("Całkiem dobra gra!")
                .imageUrl("https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png")
                .build()
        );

        gameRepository.save(Game.builder()
                .name("Gran Turismo")
                .price(12.33)
                .genre(Genre.RACING)
                .console(Console.PLAYSTATION_4)
                .description("Super gra!")
                .imageUrl("https://ecsmedia.pl/c/gran-turismo-sport-ps-hits-b-iext55519998.jpg")
                .build()
        );
    }
}
