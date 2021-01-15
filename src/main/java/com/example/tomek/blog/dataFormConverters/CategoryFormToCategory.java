package com.example.tomek.blog.dataFormConverters;

import com.sun.istack.Nullable;
import com.example.tomek.blog.formTypes.CategoryForm;
import lombok.Synchronized;
import com.example.tomek.blog.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryFormToCategory implements Converter<CategoryForm, Category> {
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryForm source){
        if (source == null){
            return null;
        }
        final Category category = new Category();
        category.setId(source.getId());
        category.setPosts(source.getPosts());
        category.setText(source.getText());
        return category;
    }
}

