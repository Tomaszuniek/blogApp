package com.example.tomek.blog.dataFormConverters;

import com.sun.istack.Nullable;
import com.example.tomek.blog.formTypes.CategoryForm;
import lombok.Synchronized;
import com.example.tomek.blog.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryForm implements Converter<Category, CategoryForm> {
    @Synchronized
    @Nullable
    @Override
    public CategoryForm convert(Category source){
        if (source == null){
            return null;
        }
        final CategoryForm categoryForm = new CategoryForm();
        categoryForm.setId(source.getId());
        categoryForm.setPosts(source.getPosts());
        categoryForm.setText(source.getText());
        return categoryForm;
    }
}
