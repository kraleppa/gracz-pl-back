package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kraleppa.model.dictionary.Role;
import pl.kraleppa.model.entity.MyUser;
import pl.kraleppa.repository.MyUserRepository;

import javax.transaction.Transactional;
import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
@Transactional
public class MyUserService{
    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    public MyUser addUser(MyUser myUser){
        myUser.setRole(Role.ROLE_USER);
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return myUserRepository.save(myUser);
    }

    public MyUser getUser(String username){
        return myUserRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public MyUser modifyUser(String username, MyUser myUser) throws Exception {
        MyUser user = getUser(username);
        if (myUser.getPassword() != null){
            myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        }

        for (Field field : myUser.getClass().getDeclaredFields()){
            field.setAccessible(true);
            if (field.get(myUser) != null){
                field.set(user, field.get(myUser));
            }
        }

        return myUserRepository.save(user);
    }
}
