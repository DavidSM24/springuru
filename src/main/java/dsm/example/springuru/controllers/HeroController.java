package dsm.example.springuru.controllers;

import dsm.example.springuru.domain.Hero;
import dsm.example.springuru.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HeroController {

    @Autowired
    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/heroes")
    public ResponseEntity<List<Hero>> getHeroes(){

        List<Hero> all=heroService.getAll();
        return new ResponseEntity<List<Hero>>(all,new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/heroes/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){

        Hero result=heroService.getById(id);
        if(result!=null) return new ResponseEntity<Hero>(result,new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity("No se ha encontrado un héroe con ese id.",new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/heroes/name/{name}")
    public ResponseEntity<List<Hero>> getByName(@PathVariable("name") String name){

        List<Hero> all=heroService.getByName(name);
        return new ResponseEntity<List<Hero>>(all,new HttpHeaders(), HttpStatus.OK);

    }

}
