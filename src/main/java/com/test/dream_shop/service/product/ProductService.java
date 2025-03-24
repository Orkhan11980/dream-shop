package com.test.dream_shop.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.dream_shop.exceptions.ProductNotFoundException;
import com.test.dream_shop.model.Category;
import com.test.dream_shop.model.Product;
import com.test.dream_shop.repository.CategoryRepository;
import com.test.dream_shop.repository.ProductRepository;
import com.test.dream_shop.request.AddProductRequest;
import com.test.dream_shop.request.UpdateProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
            .orElseGet(()-> {
                    Category newCategory  = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
            request.setCategory(category);
            return productRepository.save(createProduct(request, category));
            
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getDescription(),
            request.getInventory(),
            request.getPrice(),
            category
        );
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {

        return productRepository.findById(productId)
            .map(existingProduct -> updateExistingProduct(existingProduct, request))
            .map(productRepository::save)
            .orElseThrow(()-> new ProductNotFoundException("Product with id: " + productId + " not found"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){

        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setPrice(request.getPrice());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.findById(id)
            .ifPresentOrElse(productRepository::delete,
               ()-> {throw new ProductNotFoundException("Product with id: " + id + " not found");}
               )    ;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(()-> new ProductNotFoundException("Product with id: " + id + " not found"));
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
        
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandName(brand);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countProductsByBrandAndName(brand, name);
    }
}
