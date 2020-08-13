package pl.kraleppa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.model.Game;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.service.GameService;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public Page<Game> getAll(@RequestParam Optional<Integer> page,
                              @RequestParam Optional<Integer> size,
                              @RequestParam Optional<Console> console){
        return gameService.getAll(page, size, console);
    }
}
