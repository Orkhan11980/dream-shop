package com.test.dream_shop.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.test.dream_shop.exceptions.ResourceAlreadyExists;
import com.test.dream_shop.exceptions.ResourceNotFound;
import com.test.dream_shop.model.Category;
import com.test.dream_shop.response.ApiResponse;
import com.test.dream_shop.service.catergory.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private static final HttpStatusCode INTERNAL_SERVER_ERROR = null;
        private static final HttpStatusCode NOT_FOUND = null; 
        private final CategoryService categoryService;
    
        @GetMapping("/all")
        public ResponseEntity<ApiResponse> getAllCategories(){
            try {
                List<Category> categories = categoryService.getAllCategories();
                return ResponseEntity.ok(new ApiResponse("Categories fetched successfully", categories));
            } catch (Exception e) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
                
            }
        }
    
        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
            try {
                Category category2 = categoryService.createCategory(category);
                return ResponseEntity.ok(new ApiResponse("Category added successfully", category2));
            } catch (ResourceAlreadyExists e) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
            }
        }
    
        @GetMapping("/category/{id}/category")
        public ResponseEntity<ApiResponse> getCategoryById (@PathVariable Long id) {
    
    
            try {
                Category category = categoryService.getCategoryById(id);
                return ResponseEntity.ok(new ApiResponse("Category fetched successfully", category));
            } catch (ResourceNotFound e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{name}/category")
    public ResponseEntity<ApiResponse> getCategoryByName (@PathVariable String name) {


        try {
            Category category = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Category fetched successfully", category));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
}

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory (@PathVariable Long id) {

        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory (@RequestBody Category category, @PathVariable Long id) {

        try {
            Category category2 = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Category updated successfully", category2));
        } catch (ResourceNotFound e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
    }
    

}
