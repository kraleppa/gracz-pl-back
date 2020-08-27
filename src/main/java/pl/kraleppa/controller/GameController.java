package pl.kraleppa.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.model.Game;
import pl.kraleppa.model.dictionary.Console;
import pl.kraleppa.model.dictionary.Genre;
import pl.kraleppa.service.GameService;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam Optional<Integer> page,
                             @RequestParam Optional<Integer> size,
                             @RequestParam Optional<Console> console,
                             @RequestParam Optional<Genre> genre,
                             @RequestParam Optional<String> name,
                             @RequestParam Optional<String> sortBy,
                             @RequestParam Optional<Boolean> ascending){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(gameService.getAll(page, size, console, genre, name, sortBy, ascending));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Object> getGameById(@PathVariable Long gameId){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(gameService.getGameById(gameId).orElseThrow(
                            () -> new IllegalArgumentException("Game does not exist"))
                    );
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }

    @PostMapping
    public ResponseEntity<Object> postGame(@RequestBody Game game){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.gameService.addGame(game));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Object> putGame(@RequestBody Game game, @PathVariable Long gameId){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.gameService.editGame(game, gameId));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(e.toString());
        }
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Object> deleteGame(@PathVariable Long gameId){
        try {
            gameService.deleteGame(gameId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(null);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(e.toString());
        }
    }
}
