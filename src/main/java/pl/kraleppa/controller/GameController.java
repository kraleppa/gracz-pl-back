package pl.kraleppa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.model.Game;
import pl.kraleppa.repository.GameRepository;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameRepository gameRepository;

    @GetMapping
    public Page<Game> findAll(@RequestParam Optional<Integer> page,
                              @RequestParam Optional<Integer> size){
        return gameRepository.findAll(
                PageRequest.of(page.orElse(0), size.orElse(10))
        );
    }
}