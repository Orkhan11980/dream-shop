package com.test.dream_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.dream_shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String category);

    List<Product> findByBrandName(String brand);

    List<Product> findByName(String name);

    List<Product> findByCategoryNameAndBrand(String brand, String name);

    List<Product> findByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
