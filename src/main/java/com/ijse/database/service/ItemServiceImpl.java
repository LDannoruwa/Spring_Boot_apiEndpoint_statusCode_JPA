package com.ijse.database.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.Item;
import com.ijse.database.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{
    //Call ItemRepository to implement service
    private ItemRepository itemRepository;

    @Autowired //reduce creation process of constructor, getters, setters to wire dependency from repository to here (dependency injection). 
    public ItemServiceImpl(ItemRepository itemRepositoryQuary){
        this.itemRepository = itemRepositoryQuary;
    }

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public Item getItemByID(long id){
        return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Item is not found" + id));
    }
  
    @Override
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(long id, Item item){
        Item existingItem =getItemByID(id);

        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());
        existingItem.setCategory(item.getCategory());

        return itemRepository.save(existingItem);
    }

    @Override
    public void deleteItem(long id){
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getItemsByCategoryId(Long categoryId){
        return itemRepository.findItemsByCategoryId(categoryId);
    }
    
}
