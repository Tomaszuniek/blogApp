package com.example.tomek.blog.controllers;


import com.example.tomek.blog.dataFormConverters.CommentFormToComment;
import com.example.tomek.blog.formTypes.CategoryForm;
import com.example.tomek.blog.formTypes.CommentForm;
import com.example.tomek.blog.formTypes.PostForm;
import com.example.tomek.blog.model.Comment;
import com.example.tomek.blog.model.Post;
import com.example.tomek.blog.repositories.CategoryRepository;
import com.example.tomek.blog.services.CommentService;
import com.example.tomek.blog.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class CommentController {
    private final PostService postService;
    private final CategoryRepository categoryRepository;
    private final CommentService commentService;
    private final CommentFormToComment commentFormToComment;
    public CommentController(PostService postService, CategoryRepository categoryRepository, CommentService commentService,
                             CommentFormToComment commentFormToComment){
        this.postService = postService;
        this.categoryRepository = categoryRepository;
        this.commentService = commentService;
        this.commentFormToComment = commentFormToComment;
    }

    @GetMapping("post/{id}/comment/new")
    public String newCommentForm(@PathVariable String id, Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("idPost", id);
        return "commentForm";
    }

    @PostMapping
    @RequestMapping("post/{id}/comment/new")
    public String saveOrUpdate(@ModelAttribute Comment comment, @PathVariable String id){
        Comment savedComment = commentService.saveComment(comment, id);

        return "redirect:/posts";
    }
}

