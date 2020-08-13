package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.Game;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.repository.GameRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Page<Game> getAll(Optional<Integer> page, Optional<Integer> size, Optional<Console> console){
        Game example = Game
                .builder()
                .console(console.orElse(null))
                .build();

        return gameRepository.findAll(Example.of(example), PageRequest.of(page.orElse(0), size.orElse(10)));
    }

}
