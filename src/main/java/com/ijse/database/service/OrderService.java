package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.Orders;

//business logic is stored in the service layer. It includes validation logic in particular. The model state is used to communicate between the controller and service layers
@Service
public interface OrderService {
    List<Orders> getAllOrders();
    Orders getOrderById(long id);
    Orders createOrder(Orders order);
    Orders updateOrder(long id, Orders order);
    void deleteOrder(long id);
}
