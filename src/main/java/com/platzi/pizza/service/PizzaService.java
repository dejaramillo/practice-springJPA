package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;


    public List<PizzaEntity> getAll(){
        return pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable(){
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public List<PizzaEntity> getByWith(String ingredient){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public PizzaEntity getByName(String name){
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public PizzaEntity get(Integer idPizza){
        return pizzaRepository.findById(idPizza)
                .orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return pizzaRepository.save(pizza);
    }


    public Boolean exists(Integer idPizza){
        return pizzaRepository.existsById(idPizza);
    }

    public void delete(Integer idPizza){
        pizzaRepository.deleteById(idPizza);
    }

}
