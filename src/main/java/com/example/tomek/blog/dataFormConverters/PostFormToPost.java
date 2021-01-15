package com.example.tomek.blog.dataFormConverters;

import com.sun.istack.Nullable;
import com.example.tomek.blog.formTypes.PostForm;
import lombok.Synchronized;
import com.example.tomek.blog.model.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostFormToPost implements Converter<PostForm, Post> {
    @Synchronized
    @Nullable
    @Override
    public Post convert(PostForm source){
        if (source == null){
            return null;
        }
        final Post post = new Post();
        post.setCategories(source.getCategories());
        post.setComments(source.getComments());
        post.setDifficulty(source.getDifficulty());
        post.setId(source.getId());
        post.setImage(source.getImage());
        post.setDescription(source.getDescription());
        post.setText(source.getText());
        post.setAuthor(source.getAuthor());
        return post;
    }
}
