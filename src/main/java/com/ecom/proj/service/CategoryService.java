package com.ecom.proj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.proj.model.Category;
import com.ecom.proj.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public Optional <Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
    public void addUpdCategory(Category category) {
        categoryRepository.save(category);
    }

    public void delCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
