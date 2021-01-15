package com.example.tomek.blog.services;

import com.example.tomek.blog.formTypes.CategoryForm;
import com.example.tomek.blog.formTypes.PostForm;
import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Post;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Set<Category> findAll();

    Category findById(Long l);

    CategoryForm findFormById(Long l);

    CategoryForm saveCategoryForm(CategoryForm form);

    void deleteById(Long id);
}
