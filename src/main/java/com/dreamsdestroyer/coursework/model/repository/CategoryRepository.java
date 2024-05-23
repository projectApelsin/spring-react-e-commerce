package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);
}
