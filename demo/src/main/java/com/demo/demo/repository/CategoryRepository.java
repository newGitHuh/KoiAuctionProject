package com.demo.demo.repository;

import com.demo.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByCategoryId (String name);

}
