package com.dreamsdestroyer.coursework.service;


import com.dreamsdestroyer.coursework.model.Category;
import com.dreamsdestroyer.coursework.model.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getAllProductsCategory(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
