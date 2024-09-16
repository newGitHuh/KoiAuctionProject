package com.demo.demo.service.productService;

import com.demo.demo.model.Category;
import com.demo.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryInterface{

    private CategoryRepository categoryRepo;

    @Override
    public List<Category> getCategoryName(String name) {
        return categoryRepo.findCategoriesByCategoryId(name);
    }
}
