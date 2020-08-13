package pl.kraleppa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.kraleppa.model.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
