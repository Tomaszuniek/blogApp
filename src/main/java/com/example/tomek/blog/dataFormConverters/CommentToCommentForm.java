package com.example.tomek.blog.dataFormConverters;

import com.sun.istack.Nullable;
import com.example.tomek.blog.formTypes.CommentForm;
import lombok.Synchronized;
import com.example.tomek.blog.model.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentForm implements Converter<Comment, CommentForm> {
    @Synchronized
    @Nullable
    @Override
    public CommentForm convert(Comment source){
        if (source == null){
            return null;
        }
        final CommentForm commentForm = new CommentForm();
        commentForm.setId(source.getId());
        commentForm.setPost(source.getPost());
        commentForm.setText(source.getText());
        return commentForm;
    }
}
