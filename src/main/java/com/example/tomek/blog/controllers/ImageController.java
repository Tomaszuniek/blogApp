package com.example.tomek.blog.controllers;

import com.example.tomek.blog.formTypes.PostForm;
import com.example.tomek.blog.services.ImageService;
import com.example.tomek.blog.services.PostService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jt on 7/3/17.
 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final PostService postService;

    public ImageController(ImageService imageService, PostService postService) {
        this.imageService = imageService;
        this.postService = postService;
    }

    @GetMapping("post/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("post", postService.findFormById(Long.valueOf(id)));

        return "imageUploadForm";
    }

    @PostMapping("post/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/post/" + "show/" + id;
    }

    @GetMapping("post/{id}/postimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        PostForm postForm = postService.findFormById(Long.valueOf(id));

        if (postForm.getImage() != null) {
            byte[] byteArray = new byte[postForm.getImage().length];
            int i = 0;

            for (Byte wrappedByte : postForm.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
