package pl.kraleppa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.model.dictionary.OrderState;
import pl.kraleppa.model.entity.Order;
import pl.kraleppa.model.request.OrderOptions;
import pl.kraleppa.service.OrderService;
import pl.kraleppa.util.JwtUtil;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<Object> getOrders(@RequestHeader("Authorization") String jwt) {
        try {
            String username = jwtUtil.extractUsername(jwt.split(" ")[1]);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderService.getOrders(username));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }

    @PostMapping
    public ResponseEntity<Object> PostOrder(@RequestHeader("Authorization") String jwt,
                                            @RequestBody OrderOptions orderOptions) {
        try {
            String username = jwtUtil.extractUsername(jwt.split(" ")[1]);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderService.createOrder(username, orderOptions));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
    }

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<Object> getOrdersInProgress(@RequestParam Optional<Boolean> inProgress){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderService.getOrdersInProgress(inProgress));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
    }

    @PatchMapping
    public ResponseEntity<Object> changeOrderState(@RequestParam OrderState orderState,
                                                   @RequestParam Long orderId){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderService.changeOrderState(orderState, orderId));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(e.toString());
        }
    }

    @GetMapping
    @RequestMapping("/credentials")
    public ResponseEntity<Object> getCredentials(@RequestParam Long orderId){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderService.getCredentials(orderId));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(e.toString());
        }
    }
}
