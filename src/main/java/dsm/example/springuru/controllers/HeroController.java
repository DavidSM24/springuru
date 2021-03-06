package dsm.example.springuru.controllers;

import dsm.example.springuru.domain.Hero;
import dsm.example.springuru.services.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @ApiOperation(value = "Return all heroes", notes="Return a Hero List")
    @ApiResponses(value = {
            @ApiResponse(code=200,message="Successful Operation"),
            @ApiResponse(code=400,message="Bad Request"),
            @ApiResponse(code=404,message="ERROR, Can't get Agencies"),
            @ApiResponse(code=500,message="Internal Error"),
    })
    @GetMapping()
    public ResponseEntity<List<Hero>> getHeroes(){

        List<Hero> all=heroService.getAll();
        return new ResponseEntity<List<Hero>>(all,new HttpHeaders(), HttpStatus.OK);

    }

    @ApiOperation(value = "Return an hero by id", notes="Return a Hero filter by id.")
    @ApiResponses(value = {
            @ApiResponse(code=200,message="Successful Operation"),
            @ApiResponse(code=400,message="Bad Request"),
            @ApiResponse(code=404,message="ERROR, Can't get Agencies"),
            @ApiResponse(code=500,message="Internal Error"),
    })
    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){

        Hero result=heroService.getById(id);
        if(result!=null) return new ResponseEntity<Hero>(result,new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity("No se ha encontrado un h??roe con ese id.",new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Hero>> getByName(@PathVariable("name") String name){

        List<Hero> all=heroService.getByName(name);
        return new ResponseEntity<List<Hero>>(all,new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity add(@Validated @RequestBody Hero hero){
        Hero newHero=heroService.add(hero);
        if(newHero!=null) return new ResponseEntity<Hero>(newHero,new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity("Error al insertar, no todos los campos son correctos.",new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity update(@Validated @RequestBody Hero hero){
        Hero newHero=heroService.update(hero);
        if(newHero!=null) return new ResponseEntity<Hero>(newHero,new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity("O el id no es v??lido o alg??n campo contiene errores.",new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){

        String result;
        boolean deleted=heroService.deleteById(id);
        result="El h??roe se ha eliminado correctamente.";
        if(deleted){
            return new ResponseEntity("El h??roe se ha eliminado correctamente.",new HttpHeaders(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity("Ha ocurrido un error, su id puede no ser v??lido.",new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }
}
