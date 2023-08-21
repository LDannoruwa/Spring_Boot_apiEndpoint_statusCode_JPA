package com.ijse.database.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.Category;
import com.ijse.database.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    //Call CategoryRepository to implement service
    private CategoryRepository categoryRepository;

    @Autowired //reduce creation process of constructor, getters, setters to wire dependency from repository to here (dependency injection).  
    public CategoryServiceImpl(CategoryRepository categoryRepositoryQuary){
        this.categoryRepository = categoryRepositoryQuary;
    }

    @Override
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(long id){
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category is not found" + id));
    }

    @Override
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(long id, Category category){
        Category existinCategory = getCategoryById(id);

        existinCategory.setId(category.getId());
        existinCategory.setName(category.getName());

        return categoryRepository.save(existinCategory);
    }

    @Override
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }
}

