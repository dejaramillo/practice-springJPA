package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {


    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }


    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "8") Integer elements){
        return ResponseEntity.ok().body(this.pizzaService.getAll(page, elements));
    }


    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam(defaultValue = "8") Integer elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection ){
        return ResponseEntity.ok().body(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByWith(@PathVariable String  ingredient){
        return ResponseEntity.ok().body(this.pizzaService.getByWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String  ingredient){
        return ResponseEntity.ok().body(this.pizzaService.getWithout(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable Double price){
        return ResponseEntity.ok().body(this.pizzaService.getCheapest(price));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok().body(this.pizzaService.getByName(name));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable Integer idPizza){
        PizzaEntity pizza = this.pizzaService.get(idPizza);
        if (pizza != null){
            return ResponseEntity.ok().body(this.pizzaService.get(idPizza));
        }
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza){
        if( pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok().body(this.pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok().body(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePizzaPrice(@RequestBody UpdatePizzaPriceDto dto){
        if (Boolean.TRUE.equals(this.pizzaService.exists(dto.getPizzaId()))){
            this.pizzaService.updatePizzaPrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable Integer idPizza){
        if (Boolean.TRUE.equals(this.pizzaService.exists(idPizza))){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }



}
