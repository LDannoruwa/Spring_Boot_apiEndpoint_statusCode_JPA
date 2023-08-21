package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.User;

//business logic is stored in the service layer. It includes validation logic in particular. The model state is used to communicate between the controller and service layers
@Service
public interface UserService {
    
    //interface methods are implicitly abstract and public
    List<User> getAllUsers(); //List-> import java.util.List;
    User getUserById(long id);
    User saveUser(User user);
    User updateUser(long id, User user);
    void deleteUser(long id);    
}
