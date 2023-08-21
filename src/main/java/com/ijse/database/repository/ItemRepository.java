package com.ijse.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.Item;

//Spring boot framework provides us repository which is responsible to perform various operations on the object.
//Jpa Repository contains the APIs for basic CRUD operations, the APIS for pagination, and the APIs for sorting.

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
     //can add custom queries here.

     //Method:1 - using sql notation
     //@Query("SELECT * FROM items WHERE category_id = :categoryId")

     //Method:2 - using hibernate notation
     @Query("SELECT i FROM Item i WHERE i.category.id = :categoryId") //i indicates the Item
     List<Item> findItemsByCategoryId(@Param("categoryId") Long categoryId);
}
