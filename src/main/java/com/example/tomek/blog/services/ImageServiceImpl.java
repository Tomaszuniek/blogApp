package com.example.tomek.blog.services;

import com.example.tomek.blog.model.Post;
import com.example.tomek.blog.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final PostRepository postRepository;

    public ImageServiceImpl( PostRepository postRepository) {

        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long postId, MultipartFile file) {

        try {
            Post post = postRepository.findById(postId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            post.setImage(byteObjects);

            postRepository.save(post);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);

            e.printStackTrace();
        }
    }
}