package com.example.tomek.blog.dataFormConverters;

import com.sun.istack.Nullable;
import com.example.tomek.blog.formTypes.PostForm;
import lombok.Synchronized;
import com.example.tomek.blog.model.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostForm implements Converter<Post, PostForm> {
    @Synchronized
    @Nullable
    @Override
    public PostForm convert(Post source){
        if (source == null){
            return null;
        }
        final PostForm postForm = new PostForm();
        postForm.setCategories(source.getCategories());
        postForm.setComments(source.getComments());
        postForm.setDifficulty(source.getDifficulty());
        postForm.setId(source.getId());
        postForm.setImage(source.getImage());
        postForm.setDescription(source.getDescription());
        postForm.setText(source.getText());
        postForm.setAuthor(source.getAuthor());
        return postForm;
    }
}
