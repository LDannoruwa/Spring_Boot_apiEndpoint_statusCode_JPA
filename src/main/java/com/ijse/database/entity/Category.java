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
@Table(name = "Categories")

//Create setters and getters using Lombok
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //set primary key automatically
    private long id;

    @Column(nullable = false)
    private String name;

    //Implement 1:many relationship between Cateory:Item. (one side)
    //No need to implement relationship in both sides. You can implement the relation either in Item.java or Category.java
    //If we do so, we receive endless loop | we try to show relationship in both side
}
