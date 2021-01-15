package com.example.tomek.blog.repositories;

import com.example.tomek.blog.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.expression.spel.ast.MethodReference;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByText(String text);
    Optional<Category> findById(Long id);
}
