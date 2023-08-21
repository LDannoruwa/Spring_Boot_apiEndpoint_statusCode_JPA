package com.ijse.database.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.Orders;
import com.ijse.database.repository.OrdersRepository;

@Service
public class OrderServiceImpl implements OrderService{
    //Call ordersrRepository to implement service
    private OrdersRepository ordersRepository;

    @Autowired //reduce creation process of constructor, getters, setters to wire dependency from repository to here (dependency injection). 
    public OrderServiceImpl(OrdersRepository ordersRepositoryQuary){
        this.ordersRepository = ordersRepositoryQuary;
    }

    @Override 
    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    @Override
    public Orders getOrderById(long id){
        return ordersRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Order is not found" + id));
    }

    @Override
    public Orders createOrder(Orders order){
        return ordersRepository.save(order);
    }  
    
    @Override
    public Orders updateOrder(long id, Orders order){
        //get exixting order
        Orders existingOrder = getOrderById(id);

        //change exixting order according to order requirement
        existingOrder.setStaus(order.getStaus());

        return ordersRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(long id){
        ordersRepository.deleteById(id);
    }
}
