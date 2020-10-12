package com.hchencs.socialnetworkproject.controller;

import com.hchencs.socialnetworkproject.model.Post;
import com.hchencs.socialnetworkproject.model.PostRepository;
import com.hchencs.socialnetworkproject.model.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping(path="/post")
public class PostController {
    @Autowired//TODO: resolve warning
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping(path="/user_id")
    public @ResponseBody Iterable<Post> getPostsByUserId(@RequestParam Integer userId) {
        return postRepository.getPostsByUserIdQuery(userId);
    }

    @PostMapping(path="/create_post")
    public @ResponseBody String createPost(@RequestParam Integer userId, @RequestParam String content) {
        userRepository.findById(userId)
                .ifPresent(user -> postRepository.save(Post.builder()
                        .user(user)
                        .content(content)
                        .publishDate(DateTime.now().toDate())
                        .build()));
        return "Saved";
    }
}
