package com.ijse.database.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

// An entity class represents a table in a relational database
@Entity
@Table(name="items")
@EqualsAndHashCode(exclude = "orders") // to exclude order (not a correct way)

//Create setters and getters using Lombok
@Data //without using @Getter and @Setter method, you can use @Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //set primary key automatically
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    //Implement 1:many relationship between Cateory:Item. (Many side)
    @ManyToOne(fetch = FetchType.EAGER) //the fetch type of the Many:1 should be EAGER. //LAZY - give if we only request| EAGER - always give
    @JoinColumn(name = "category_id") //Foregin key //It does not matter what the name you use as a name beause this is the name of JoinColumn.
    private Category category;    //By this statement tables will connect each other by the primary keys.

    //set ManytoMany relationship between Item and Order
    //should implement relationship in both sides. You should implement the relation in Item.java and Orders.java
    @ManyToMany(mappedBy = "item") 
    private Set<Orders> orders = new HashSet<>();
}
