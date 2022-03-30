package dsm.example.springuru.controllers;

import dsm.example.springuru.domain.Hero;
import dsm.example.springuru.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/heroes")
@Controller
public class HeroController {

    @Autowired
    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping()
    public ResponseEntity<List<Hero>> getHeroes(){

        List<Hero> all=heroService.getAll();
        return new ResponseEntity<List<Hero>>(all,new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){

        Hero result=heroService.getById(id);
        if(result!=null) return new ResponseEntity<Hero>(result,new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity("No se ha encontrado un héroe con ese id.",new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Hero>> getByName(@PathVariable("name") String name){

        List<Hero> all=heroService.getByName(name);
        return new ResponseEntity<List<Hero>>(all,new HttpHeaders(), HttpStatus.OK);

    }

    @PutMapping()
    public ResponseEntity update(@Validated @RequestBody Hero hero){
        Hero newHero=heroService.update(hero);
        if(newHero!=null) return new ResponseEntity<Hero>(newHero,new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity("O el id no es válido o algún campo contiene errores.",new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){

        String result;
        boolean deleted=heroService.deleteById(id);
        result="El héroe se ha eliminado correctamente.";
        if(deleted){
            return new ResponseEntity("El héroe se ha eliminado correctamente.",new HttpHeaders(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity("Ha ocurrido un error, su id puede no ser válido.",new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }
}
