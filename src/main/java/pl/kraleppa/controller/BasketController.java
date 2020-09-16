package pl.kraleppa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.service.BasketService;
import pl.kraleppa.util.JwtUtil;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/baskets")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<Object> getBasket(@RequestHeader("Authorization") String jwt){
        try {
            String username = jwtUtil.extractUsername(jwt.split(" ")[1]);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(basketService.getBasket(username));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addGameToBasket(@RequestHeader("Authorization") String jwt,
                                                  @RequestParam Optional<Long> gameId){
        try {
            String username = jwtUtil.extractUsername(jwt.split(" ")[1]);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(basketService.addGameToBasket(username, gameId.orElseThrow(IllegalArgumentException::new)));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
}
