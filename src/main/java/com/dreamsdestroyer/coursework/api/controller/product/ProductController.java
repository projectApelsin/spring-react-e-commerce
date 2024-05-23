/*package com.dreamsdestroyer.CourseWork.api.controller.product;

import com.dreamsdestroyer.CourseWork.model.Category;
import com.dreamsdestroyer.CourseWork.model.LocalUser;
import com.dreamsdestroyer.CourseWork.model.Product;
import com.dreamsdestroyer.CourseWork.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/getProductById")
    public ResponseEntity<Product> getProductById(Long id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

}
*/