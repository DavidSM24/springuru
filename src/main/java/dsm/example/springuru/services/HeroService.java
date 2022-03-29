package dsm.example.springuru.services;

import dsm.example.springuru.domain.Hero;
import dsm.example.springuru.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class HeroService {

    @Autowired
    HeroRepository repository;

    public List<Hero> getAll() {

        return repository.findAll();
    }

    public Hero getById(Long id){

        Optional<Hero> h=repository.findById(id);
        return h.orElse(null);

    }


    public List<Hero> getByName(String name){
        return repository.getByName(name.toLowerCase());
    }
}
