package com.ijse.database.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.User;
import com.ijse.database.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

   //Call UserRepository to implement service
   private UserRepository userRepository;

   @Autowired //reduce creation process of constructor, getters, setters to wire dependency from repository to here (dependency injection). 
   public UserServiceImpl(UserRepository userRepositoryQuary){
      this.userRepository = userRepositoryQuary;
   }

   //Implement methods in UserService interface
   @Override
   public List<User> getAllUsers(){
    return userRepository.findAll();
   }
   
   @Override
   public User getUserById(long id){
    return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User is not found" + id));
   }
   
   @Override
   public User saveUser(User user){
    return userRepository.save(user);
   }

   @Override
   public User updateUser(long id, User user){
      //get existing user
      User existingUser = getUserById(id);

      //change existing user according to our requirement
      existingUser.setUsername(user.getUsername());
      existingUser.setEmail(user.getUsername());
      existingUser.setPassword(user.getPassword());

      return userRepository.save(existingUser);
   }

   @Override
   public void deleteUser(long id){
      userRepository.deleteById(id);
   }
}
