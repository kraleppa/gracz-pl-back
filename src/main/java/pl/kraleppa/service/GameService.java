package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.Game;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.model.dictionary.Genre;
import pl.kraleppa.repository.GameRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Page<Game> getAll(Optional<Integer> page,
                             Optional<Integer> size,
                             Optional<Console> console,
                             Optional<Genre> genre,
                             Optional<String> name,
                             Optional<String> sortBy,
                             Optional <Boolean> ascending){
        Game example = Game
                .builder()
                .console(console.orElse(null))
                .genre(genre.orElse(null))
                .name(name.orElse(null))
                .build();

        return gameRepository.findAll(Example.of(example),
                PageRequest.of(page.orElse(0), size.orElse(10),
                        ascending.orElse(true) ? Sort.by(sortBy.orElse("gameId")).ascending().and(Sort.by("gameId")) :
                                Sort.by(sortBy.orElse("gameId")).descending().and(Sort.by("gameId"))
                        )
        );

    }

    public Game addGame(Game game){
        return gameRepository.save(game);
    }

    public Optional<Game> getGameById(Long gameId){
        return gameRepository.findById(gameId);
    }

    public Game editGame(Game game, Long gameId){
        game.setGameId(gameId);
        return gameRepository.save(game);
    }

    public void deleteGame(Long gameId){
        gameRepository.deleteById(gameId);
    }

}
