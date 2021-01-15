package com.example.tomek.blog.repositories;

import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("select p from post p WHERE :category in elements(p.categories)")
    List<Post> findAllByCategory(@Param("category") Category category);
}
