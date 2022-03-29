package dsm.example.springuru.repositories;

import dsm.example.springuru.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends JpaRepository<Hero,Long> {

}
