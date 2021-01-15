package com.example.tomek.blog.services;

import com.example.tomek.blog.dataFormConverters.CategoryFormToCategory;
import com.example.tomek.blog.dataFormConverters.CategoryToCategoryForm;
import com.example.tomek.blog.dataFormConverters.CommentFormToComment;
import com.example.tomek.blog.dataFormConverters.CommentToCommentForm;
import com.example.tomek.blog.formTypes.CategoryForm;
import com.example.tomek.blog.formTypes.CommentForm;
import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Comment;
import com.example.tomek.blog.model.Post;
import com.example.tomek.blog.repositories.CategoryRepository;
import com.example.tomek.blog.repositories.CommentRepository;
import com.example.tomek.blog.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentFormToComment commentFormToComment;
    private final CommentToCommentForm commentToCommentForm;
    private final PostRepository postRepository;


    public CommentServiceImpl(CommentRepository commentRepository, CommentFormToComment commentFormToComment,
                              CommentToCommentForm commentToCommentForm, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentFormToComment = commentFormToComment;
        this.commentToCommentForm = commentToCommentForm;
        this.postRepository = postRepository;
    }

    @Override
    public CommentForm saveCommentForm(CommentForm form) {
        Comment convertedComment = commentFormToComment.convert(form);
        Comment savedComment = commentRepository.save(convertedComment);
        log.debug("Saved Category: {} with ID {}", savedComment.getText(), savedComment.getId());
        return commentToCommentForm.convert(convertedComment);
    }

    @Override
    public Comment saveComment(Comment comment, String idPost) {
        Post post = postRepository.findById(Long.valueOf(idPost)).get();
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        log.debug("Saved Category: {} with ID {}", savedComment.getText(), savedComment.getId());
        return comment;
    }
    @Override
    public Comment saveComment(Comment comment) {
        return comment;
    }
}
