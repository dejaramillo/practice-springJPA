package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.service.OrderService;
import com.platzi.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private  final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        var response = this.orderService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders(){
        var response = this.orderService.getTodayOrders();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders(){
        var response = this.orderService.getOutSideOrders();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer){
        var response = this.orderService.getCustomerOrders(idCustomer);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable Integer orderId){
        return ResponseEntity.ok().body(this.orderService.getSummary(orderId));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> saveRandomOrder(@RequestBody RandomOrderDto dto){
        return ResponseEntity.ok().body(this.orderService.saveRandomOrder(dto));
    }

}
