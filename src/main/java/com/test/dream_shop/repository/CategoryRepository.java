package com.test.dream_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.dream_shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findByName(String name);

    Category getCategoryById(Long id);

    boolean existsByName(String name);
    

}
 