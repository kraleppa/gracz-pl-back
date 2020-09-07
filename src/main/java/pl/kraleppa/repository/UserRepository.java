package pl.kraleppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kraleppa.model.entity.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findUserByUserName(String userName);
}
