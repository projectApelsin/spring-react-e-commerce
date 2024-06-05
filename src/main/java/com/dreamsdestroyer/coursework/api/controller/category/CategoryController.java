package com.dreamsdestroyer.coursework.api.controller.category;


import com.dreamsdestroyer.coursework.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import com.dreamsdestroyer.coursework.service.CategoryService;

import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/getProductsById")
    public Category getCategoryById(Long id) {
        Category category = categoryService.getAllProductsCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK).getBody();
    }
}
