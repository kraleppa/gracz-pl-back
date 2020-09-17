package pl.kraleppa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public MyUser addUser(MyUser myUser){
        myUser.setRole(Role.ROLE_USER);
        return myUserRepository.save(myUser);
    }

    public MyUser getUser(String username){
        return myUserRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public MyUser modifyUser(String username, MyUser myUser) throws Exception {
        MyUser user = getUser(username);

        for (Field field : myUser.getClass().getDeclaredFields()){
            field.setAccessible(true);
            if (field.get(myUser) != null){
                field.set(user, field.get(myUser));
            }
        }

        return myUserRepository.save(user);
    }
}
