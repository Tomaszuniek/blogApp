package com.example.tomek.blog.services;

import com.example.tomek.blog.dataFormConverters.PostFormToPost;
import com.example.tomek.blog.dataFormConverters.PostToPostForm;
import com.example.tomek.blog.formTypes.PostForm;
import com.example.tomek.blog.model.Category;
import lombok.extern.slf4j.Slf4j;
import com.example.tomek.blog.model.Post;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.example.tomek.blog.repositories.PostRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostFormToPost postFormToPost;
    private final PostToPostForm postToPostForm;

    public PostServiceImpl(PostRepository postRepository, PostFormToPost postFormToPost,
                             PostToPostForm postToPostForm) {
        this.postRepository = postRepository;
        this.postFormToPost = postFormToPost;
        this.postToPostForm = postToPostForm;
    }

    @Override
    @Transactional
    public PostForm findFormById(Long l){
        return postToPostForm.convert(findById(l));
    }

    @Override
    public Set<Post> getPost() {
        Set<Post> postSet = new HashSet<>();
        postRepository.findAll().iterator().forEachRemaining(postSet::add);
        return postSet;
    }

    @Override
    public Post findById(Long l) {

        Optional<Post> postOptional = postRepository.findById(l);

        if (!postOptional.isPresent()) {
            throw new RuntimeException("Post Not Found!");
        }

        return postOptional.get();
    }

    @Override
    public Set<Post> findAll(){
        Set<Post> postSet = new HashSet<>();
        postRepository.findAll().iterator().forEachRemaining(postSet::add);
        return postSet;
    }

    @Override
    @Transactional
    public PostForm savePostForm(PostForm form){
        Post convertedPost = postFormToPost.convert(form);
        Post savedPost = postRepository.save(convertedPost);
        log.debug("Saved Person: {} with ID {}", savedPost.getId(), savedPost.getId());
        return postToPostForm.convert(savedPost);
    }

    @Override
    @Transactional
    public Post savePost(Post post){
        Post savedPost = postRepository.save(post);
        log.debug("Saved Person: {} with ID {}", savedPost.getId(), savedPost.getId());
        return post;
    }

    @Override
    public List<Post> findAllByCategory(Category category){
        List<Post> postsByCategory = postRepository.findAllByCategory(category);
        return postsByCategory;
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
