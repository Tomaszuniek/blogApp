package com.example.tomek.blog.services;

import com.example.tomek.blog.dataFormConverters.CategoryFormToCategory;
import com.example.tomek.blog.dataFormConverters.CategoryToCategoryForm;
import com.example.tomek.blog.dataFormConverters.PostFormToPost;
import com.example.tomek.blog.dataFormConverters.PostToPostForm;
import com.example.tomek.blog.formTypes.CategoryForm;
import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Post;
import com.example.tomek.blog.repositories.CategoryRepository;
import com.example.tomek.blog.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryFormToCategory categoryFormToCategory;
    private final CategoryToCategoryForm categoryToCategoryForm;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryFormToCategory categoryFormToCategory,
                               CategoryToCategoryForm categoryToCategoryForm) {
        this.categoryRepository = categoryRepository;
        this.categoryFormToCategory = categoryFormToCategory;
        this.categoryToCategoryForm = categoryToCategoryForm;
    }

    @Override
    public Set<Category> findAll() {
        Set<Category> categorySet = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categorySet::add);
        return categorySet;
    }

    @Override
    public Category findById(Long l) {
        Optional<Category> categoryOptional = categoryRepository.findById(l);

        if (!categoryOptional.isPresent()) {
            throw new RuntimeException("Category Not Found!");
        }

        return categoryOptional.get();
    }

    @Override
    public CategoryForm findFormById(Long l) {
        return null;
    }

    @Override
    public CategoryForm saveCategoryForm(CategoryForm form) {
        Category convertedCategory = categoryFormToCategory.convert(form);
        Category savedCategory = categoryRepository.save(convertedCategory);
        log.debug("Saved Category: {} with ID {}", savedCategory.getText(), savedCategory.getId());
        return categoryToCategoryForm.convert(convertedCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
