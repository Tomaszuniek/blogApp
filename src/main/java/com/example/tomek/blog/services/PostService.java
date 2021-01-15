package com.example.tomek.blog.services;

import com.example.tomek.blog.formTypes.PostForm;
import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Post;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PostService {
    Set<Post> getPost();

    Set<Post> findAll();

    Post findById(Long l);

    PostForm findFormById(Long l);

    PostForm savePostForm(PostForm form);

    Post savePost(Post post);

    void deleteById(Long id);

    List<Post> findAllByCategory(@Param("category") Category category);
}
