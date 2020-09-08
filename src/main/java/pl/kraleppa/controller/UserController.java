package pl.kraleppa.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.service.MyUserService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final MyUserService myUserService;

    @PostMapping
    public ResponseEntity<Object> postGame(@RequestBody MyUser myUser){
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
}
