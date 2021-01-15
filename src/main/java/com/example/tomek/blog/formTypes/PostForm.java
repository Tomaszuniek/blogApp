package com.example.tomek.blog.formTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.tomek.blog.model.Category;
import com.example.tomek.blog.model.Comment;
import com.example.tomek.blog.model.Difficulty;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {
    private Long id;
    private Difficulty difficulty;
    private List<Category> categories = new ArrayList<Category>();
    private Byte[] image;
    private List<Comment> comments = new ArrayList<Comment>();
    private String text = "";
    private String author = "";
    private String description = "";
}
