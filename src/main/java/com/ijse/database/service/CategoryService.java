package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.Category;

////business logic is stored in the service layer. It includes validation logic in particular. The model state is used to communicate between the controller and service layers
@Service
public interface CategoryService {
    //interface methods are implicitly abstract and public
    List<Category> getAllCategory(); //List-> import java.util.List;
    Category getCategoryById(long id);
    Category createCategory(Category category);
    Category updateCategory(long id, Category category);
    void deleteCategory(long id);    
}
