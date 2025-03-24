package com.test.dream_shop.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.test.dream_shop.model.Category;
import com.test.dream_shop.response.ApiResponse;
import com.test.dream_shop.service.catergory.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public ResponseEntity<ApiResponse> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new ApiResponse("Categories fetched successfully", categories));
    }

}
