package com.example.tomek.blog.dataFormConverters;

import com.sun.istack.Nullable;
import com.example.tomek.blog.formTypes.CommentForm;
import lombok.Synchronized;
import com.example.tomek.blog.model.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentFormToComment implements Converter<CommentForm, Comment> {
    @Synchronized
    @Nullable
    @Override
    public Comment convert(CommentForm source){
        if (source == null){
            return null;
        }
        final Comment comment = new Comment();
        comment.setId(source.getId());
        comment.setPost(source.getPost());
        comment.setText(source.getText());
        return comment;
    }
}
