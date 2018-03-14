package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author teyma
 * @since 11/03/2018
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<Category> getcategories() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategory(Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public void addCategory(@RequestBody Category category) {
        repository.save(category);
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category) {
        repository.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(Long id) {
        repository.deleteById(id);

    }
}
