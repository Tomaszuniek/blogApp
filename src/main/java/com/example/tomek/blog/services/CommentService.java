package com.example.tomek.blog.services;

import com.example.tomek.blog.formTypes.CategoryForm;
import com.example.tomek.blog.formTypes.CommentForm;
import com.example.tomek.blog.model.Comment;

public interface CommentService {
    CommentForm saveCommentForm(CommentForm form);
    Comment saveComment(Comment comment);
    Comment saveComment(Comment comment, String idPost);
}
