package pl.kraleppa.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.service.MyUserService;
import pl.kraleppa.util.JwtUtil;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final MyUserService myUserService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Object> postUser(@RequestBody MyUser myUser){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.myUserService.addUser(myUser));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getUser(@RequestHeader("Authorization") String jwt){
        try {
            String username = jwtUtil.extractUsername(jwt.split(" ")[1]);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(myUserService.getUser(username));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
    }

    @PatchMapping
    public ResponseEntity<Object> patchUser(@RequestHeader("Authorization") String jwt, @RequestBody MyUser myUser){
        try {

            String username = jwtUtil.extractUsername(jwt.split(" ")[1]);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(myUserService.modifyUser(username, myUser));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(e.toString());
        }
    }
}
