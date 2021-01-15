package com.example.tomek.blog.controllers;

import com.example.tomek.blog.formTypes.CategoryForm;
import com.example.tomek.blog.formTypes.PostForm;
import com.example.tomek.blog.repositories.CategoryRepository;
import com.example.tomek.blog.repositories.CommentRepository;
import com.example.tomek.blog.repositories.PostRepository;
import com.example.tomek.blog.services.CategoryService;
import com.example.tomek.blog.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class CategoryController {
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final CommentRepository commentRepository;
    public CategoryController(PostRepository postRepository, CategoryService categoryService, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.categoryService = categoryService;
        this.commentRepository = commentRepository;
    }

    @RequestMapping({"/categories","/categories/index"})
    public String getCategoriesPage(Model model){
        model.addAttribute("categories", categoryService.findAll());

        return "categories";
    }

    @PostMapping
    @RequestMapping("category")
    public String saveOrUpdate(@ModelAttribute CategoryForm categoryForm){
        CategoryForm savedCategory = categoryService.saveCategoryForm(categoryForm);

        return "redirect:/category/show/" + savedCategory.getId();
    }

    @GetMapping("newcategory")
    public String showUploadForm(Model model){
        model.addAttribute("category", new CategoryForm());

        return "categoryForm";
    }

    @RequestMapping({"/category/show/{id}"})
    public String showCategory(@PathVariable String id, Model model){
        model.addAttribute("category", categoryService.findById(Long.valueOf(id)));

        return "category";
    }
}
