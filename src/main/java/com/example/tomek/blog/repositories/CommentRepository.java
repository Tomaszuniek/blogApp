package com.example.tomek.blog.repositories;

import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findByText(String text);
}
