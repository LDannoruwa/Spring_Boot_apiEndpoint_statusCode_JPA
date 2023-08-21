package com.ijse.database.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.entity.Orders;
import com.ijse.database.service.OrderService;

//Controller class is responsible for processing incoming REST API requests, preparing a model, and returning the view to be rendered as a response.
@RestController
@RequestMapping("/orders") //serve entire controller under users url 
public class OrdersController {
    //Call OrderService to implement controller
    private OrderService orderService;

    @Autowired //reduce creation process of constructor, getters, setters to wire dependency from service to here (dependency injection).  
    public OrdersController(OrderService orderService){
        this.orderService = orderService;
    } 

    @GetMapping //default mapping
    public ResponseEntity<List<Orders>> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable long id){
        try {
            //Try to find user by id
            Orders orders = orderService.getOrderById(id);
            return ResponseEntity.status(HttpStatus.OK).body(orders);

        } catch (NoSuchElementException e) {
            //If there is no user by id, throw NoSuchElementException from UserServiceIml.java
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders){
          try {
            //Try to find user by id
            Orders ordersControll = orderService.createOrder(orders);
            return ResponseEntity.status(HttpStatus.CREATED).body(ordersControll);

        } catch (NoSuchElementException e) {
            //If there is no user by id, throw NoSuchElementException from UserServiceIml.java
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable long id, @RequestBody Orders orders){
        try {
            Orders updateOrders = orderService.updateOrder(id, orders);
            return ResponseEntity.status(HttpStatus.OK).body(updateOrders);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id){
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    
}
