package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;


    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok().body(pizzaService.getAll());
    }


    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok().body(pizzaService.getAvailable());
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByWith(@PathVariable String  ingredient){
        return ResponseEntity.ok().body(pizzaService.getByWith(ingredient));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok().body(pizzaService.getByName(name));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable Integer idPizza){
        PizzaEntity pizza = pizzaService.get(idPizza);
        if (pizza != null){
            return ResponseEntity.ok().body(pizzaService.get(idPizza));
        }
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza){
        if( pizza.getIdPizza() == null || !pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok().body(pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() != null && pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok().body(pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable Integer idPizza){
        if (Boolean.TRUE.equals(pizzaService.exists(idPizza))){
            pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
