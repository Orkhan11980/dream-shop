package com.test.dream_shop.service.product;

import java.util.List;

import com.test.dream_shop.model.Product;
import com.test.dream_shop.request.AddProductRequest;
import com.test.dream_shop.request.UpdateProductRequest;

public interface IProductService {

    Product addProduct(AddProductRequest product);

    Product updateProduct(UpdateProductRequest product, Long productId);

    void deleteProduct(Long id);

    Product getProductById(Long id);

    List<Product> getProductByName(String name);

    List<Product> getAllProducts();

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductByCategory(String category);

    List<Product> getProductByCategoryAndBrand(String category, String brand);

    List<Product> getProductByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);



}
