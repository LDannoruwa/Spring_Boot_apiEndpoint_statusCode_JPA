package com.ijse.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// An entity class represents a table in a relational database

@Entity
@Table(name = "users") //Set table name //If we didn't use this spring search table name as User (Entity name). 

//setters and getters using Lombok
@Getter
@Setter
public class User {
    //Define columns in table

    @Id //set primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //set primary key automatically
    private long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    //Implement 1:many relationship between User:Orders. (one side)
    //No need to implement relationship in both sides. You can implement the relation either in User.java or Orders.java
    //If we do so, we receive endless loop | we try to show relationship in both side
}