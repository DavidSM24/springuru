package dsm.example.springuru.repositories;

import dsm.example.springuru.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero,Long> {

    @Query(value = "SELECT * FROM HERO WHERE LOWER(NAME) LIKE %:name%", nativeQuery = true)
    public List<Hero> getByName(@Param("name") String name);
}
