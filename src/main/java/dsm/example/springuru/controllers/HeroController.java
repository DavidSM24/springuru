package dsm.example.springuru.controllers;

import dsm.example.springuru.domain.Hero;
import dsm.example.springuru.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HeroController {

    @Autowired
    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @RequestMapping("/heroes")
    public ResponseEntity<List<Hero>> getAuthors(Model model){

        List<Hero> all=heroService.getAll();
        return new ResponseEntity<List<Hero>>(all,new HttpHeaders(), HttpStatus.OK);

    }
}
