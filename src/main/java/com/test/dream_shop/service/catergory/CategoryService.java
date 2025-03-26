package com.test.dream_shop.service.catergory;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.dream_shop.exceptions.ResourceAlreadyExists;
import com.test.dream_shop.exceptions.ResourceNotFound;
import com.test.dream_shop.model.Category;
import com.test.dream_shop.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    
    @Override
    public Category createCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new ResourceAlreadyExists("Category already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(categoryRepository.getCategoryById(id))
            .map(oldCategory -> {
                oldCategory.setName(category.getName());
                oldCategory.setDescription(category.getDescription());
                return categoryRepository.save(oldCategory);
            })
            .orElseThrow(() -> new ResourceNotFound("Category not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
            .ifPresentOrElse(category -> categoryRepository.delete(category),
                () -> {
                    throw new ResourceNotFound("Category not found");
                });
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
