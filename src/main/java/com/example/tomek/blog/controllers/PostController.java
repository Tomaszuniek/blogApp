package com.example.tomek.blog.controllers;

import com.example.tomek.blog.formTypes.PostForm;
import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.repositories.CategoryRepository;
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
public class PostController {
    private final PostService postService;
    private final CategoryRepository categoryRepository;
    public PostController(PostService postService, CategoryRepository categoryRepository){
        this.postService = postService;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping({"/posts","/posts/index"})
    public String getPersonsPage(Model model){
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("allCategories", categoryRepository.findAll());
        return "posts";
    }

    @RequestMapping("/post/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("post", postService.findById(Long.valueOf(id)));
        return "post";
    }

    @GetMapping
    @RequestMapping("/posts/new")
    public String newPost(Model model){
        model.addAttribute("post", new PostForm());
        model.addAttribute("categoriesFromRepo", categoryRepository.findAll());
        model.addAttribute("categoryRepo", categoryRepository);


        return "postForm";
    }

    @PostMapping
    @RequestMapping("post")
    public String saveOrUpdate(@ModelAttribute PostForm postForm){
        PostForm savedPost = postService.savePostForm(postForm);

        return "redirect:/post/show/" + savedPost.getId();
    }


    @GetMapping
    @RequestMapping("post/{id}/update")
    public String updatePost(@PathVariable String id, Model model){
        {
            model.addAttribute("categoriesFromRepo", categoryRepository.findAll());
            model.addAttribute("categoryRepo", categoryRepository);
            model.addAttribute("post", postService.findById(Long.valueOf(id)));
            return  "postForm";
        }
    }

    @GetMapping("post/{id}/delete")
    public String deletePost(@PathVariable String id){
        {
            log.debug("Deleting post: {}", id);

            postService.deleteById(Long.valueOf(id));
            return  "redirect:/";
        }
    }

    @GetMapping("posts/category/{id}")
    public String shoByCategory(@PathVariable String id, Model model){
        {
            Category category = categoryRepository.findById(Long.valueOf(id)).get();
            model.addAttribute("posts", postService.findAllByCategory(category));
            return  "posts";
        }
    }
}
