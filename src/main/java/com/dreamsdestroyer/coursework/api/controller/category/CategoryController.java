/*package com.dreamsdestroyer.CourseWork.api.controller.category;

import com.dreamsdestroyer.CourseWork.model.Category;
import com.dreamsdestroyer.CourseWork.model.repository.CategoryRepository;
import com.dreamsdestroyer.CourseWork.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

   @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/getProductsById")
    public Category getCategoryById(Long id){
        Category category = categoryService.getAllProductsCategory(id);
        return new ResponseEntity<>(category,HttpStatus.OK).getBody();
    }
}
*/