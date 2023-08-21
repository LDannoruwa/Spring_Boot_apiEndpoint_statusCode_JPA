package com.ijse.database.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

// An entity class represents a table in a relational database
@Entity
@Table(name = "orders")

//Create setters and getters using Lombok
@Data //without using @Getter and @Setter method, you can use @Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //set primary key automatically
    private Long id;

    @Column(updatable = false) //is not allowed to update
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(updatable = false) //is not allowed to update
    private String staus;

    //set ManyToOne relationship between User and Orders
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") //foreign key of user entity
    private User user;

    //set ManyToMany relationship between Item and Orders
    //should implement relationship in both sides. You should implement the relation in Item.java and Orders.java
   @ManyToMany(cascade = CascadeType.ALL)
   //create intermediate table in ManaToMany relationship
   @JoinTable(
    name = "order_items", 
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> item = new HashSet<>();

    //-----------------------------[methods: related to createdAt and updatedAt]--------------------------------------
    @PrePersist //before create something in database
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate //before update something in database
    protected void onUpdae(){
        this.updatedAt = LocalDateTime.now();
    }
    //----------------------------------------------------------------------------------------------------------------
}
