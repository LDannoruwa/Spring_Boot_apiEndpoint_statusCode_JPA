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
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.entity.Item;
import com.ijse.database.service.ItemService;

//Controller class is responsible for processing incoming REST API requests, preparing a model, and returning the view to be rendered as a response.
@RestController
//@RequestMapping("/items") //This one is removed, because we use custome mapping here. Special case : getItemsByCategoryId
public class ItemController {
    //Call ItemService to implement controller
    private ItemService itemService;

    @Autowired //reduce creation process of constructor, getters, setters to wire dependency from service to here (dependency injection). 
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable long id){
        try {
            Item item = itemService.getItemByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Item> saveItem(@RequestBody Item item){
        try {
            Item itemControll = itemService.saveItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(itemControll);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item item){
        try {
            Item updateItem = itemService.updateItem(id, item);
            return ResponseEntity.status(HttpStatus.OK).body(updateItem);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id){
        try {
            itemService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/categories/{categoryId}/items")
    public ResponseEntity<List<Item>> getItemsByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemsByCategoryId(categoryId));
    }
}
