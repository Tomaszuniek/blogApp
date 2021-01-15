package com.example.tomek.blog.formTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.tomek.blog.model.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {
    private Long id;
    private List<Post> posts = new ArrayList<Post>();
    private String text = "";
}
