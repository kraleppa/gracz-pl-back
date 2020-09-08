package pl.kraleppa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.kraleppa.model.request.AuthenticationRequest;
import pl.kraleppa.model.request.AuthenticationResponse;
import pl.kraleppa.service.MyUserDetailsService;
import pl.kraleppa.util.JwtUtil;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            UserDetails userDetails = myUserDetailsService
                    .loadUserByUsername(request.getUsername());

            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new AuthenticationResponse(jwt));
        } catch (BadCredentialsException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.toString());
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.toString());
        }
    }
}
