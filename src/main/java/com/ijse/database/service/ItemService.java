package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.Item;

//business logic is stored in the service layer. It includes validation logic in particular. The model state is used to communicate between the controller and service layers
@Service
public interface ItemService {
    List<Item> getAllItems();
    Item getItemByID(long id);
    Item saveItem(Item item);
    Item updateItem(long id, Item item);
    void deleteItem(long id);
    List<Item> getItemsByCategoryId(Long categoryId);
}
