package pl.kraleppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kraleppa.model.entity.MyUser;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findUserByUsername(String username);
}
