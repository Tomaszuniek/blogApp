package com.example.tomek.blog.formTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.tomek.blog.model.Post;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {
    private Long id;
    private Post post;
    private String text = "";
}
