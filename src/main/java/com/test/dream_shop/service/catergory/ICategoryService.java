package com.test.dream_shop.service.catergory;

import java.util.List;

import com.test.dream_shop.model.Category;


public interface ICategoryService {

    Category createCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategory(Long id);

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    
}
