package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.dictionary.Role;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.repository.MyUserRepository;

@Service
@RequiredArgsConstructor
public class MyUserService{
    private final MyUserRepository myUserRepository;

    public MyUser addUser(MyUser myUser){
        myUser.setRole(Role.ROLE_USER);
        return myUserRepository.save(myUser);
    }

    public MyUser getUser(String username){
        return myUserRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
