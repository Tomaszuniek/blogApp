package com.example.tomek.blog.bootstrap;

import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Comment;
import com.example.tomek.blog.model.Difficulty;
import com.example.tomek.blog.model.Post;
import com.example.tomek.blog.repositories.CategoryRepository;
import com.example.tomek.blog.repositories.CommentRepository;
import com.example.tomek.blog.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class BootstrapRunner implements ApplicationListener<ContextRefreshedEvent> {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public BootstrapRunner(
            PostRepository postRepository,
            CategoryRepository categoryRepository,
            CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        postRepository.saveAll(getPosts());
        log.debug("Loading Bootstrap Data");
    }


    private List<Post> getPosts() {

        List<Post> posts = new ArrayList<Post>();

        Optional<Category> java = categoryRepository.findByText("JAVA");
        Optional<Category> web = categoryRepository.findByText("Web Development");
        Optional<Category> php = categoryRepository.findByText("PHP");


        Post aboutJava = new Post();
        aboutJava.getCategories().add(java.get());
        aboutJava.getCategories().add(web.get());
        aboutJava.setDifficulty(Difficulty.AVERAGE);
        aboutJava.setText("Hello, this is the first Post about web development in Java");
        aboutJava.setAuthor("Tomasz Gorka");
        aboutJava.setDescription("Java usage in web development");

        Post phpService = new Post();
        phpService.getCategories().add(php.get());
        phpService.getCategories().add(web.get());
        phpService.setDifficulty(Difficulty.ADVANCED);
        phpService.setText("Hello, in this article we will talk about PHP");
        phpService.setAuthor("Tomasz Gorka");
        phpService.setDescription("Why do we use PHP in web development?");

        Comment comment1 = new Comment();
        comment1.setText("Wow, that was so great!");
        Comment comment2 = new Comment();
        comment2.setText("Well, that was lame");

        aboutJava.getComments().add(comment1);
        phpService.getComments().add(comment2);
        comment1.setPost(aboutJava);
        comment2.setPost(phpService);

        posts.add(phpService);
        posts.add(aboutJava);

        return posts;
    }
}
