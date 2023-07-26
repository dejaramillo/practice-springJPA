package com.platzi.pizza.persistence.audit;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;

@Slf4j
public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity pizza){
        log.info("Post load");
        this.currentValue = SerializationUtils.clone(pizza);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizza){
        log.info("Post persist or update");
        log.info("Old value: {}", this.currentValue);
        log.info("new value: {}", pizza.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity pizza){
        log.info(pizza.toString());
    }

}
