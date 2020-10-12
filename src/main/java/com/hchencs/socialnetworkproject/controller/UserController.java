package com.hchencs.socialnetworkproject.controller;

import com.hchencs.socialnetworkproject.model.FollowRelation;
import com.hchencs.socialnetworkproject.model.FollowRelationRepository;
import com.hchencs.socialnetworkproject.model.User;
import com.hchencs.socialnetworkproject.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired//TODO: resolve warning
    private UserRepository userRepository;

    @Autowired
    private FollowRelationRepository followRelationRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/create_user")
    public @ResponseBody String createUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
        userRepository.save(user);

        return "Saved";
    }

    @PostMapping(path="/follow")
    public @ResponseBody String follow(@RequestParam Integer followerId, @RequestParam Integer followeeId) {
        Optional<User> follower = userRepository.findById(followerId);
        Optional<User> followee = userRepository.findById(followeeId);
        if (follower.isEmpty() || followee.isEmpty()) return "Invalid operation";

        followRelationRepository.save(
                FollowRelation.builder()
                        .follower(follower.get())
                        .followee(followee.get())
                .build());

        return "Saved";
    }

    @GetMapping(path="/followers")
    public @ResponseBody Iterable<User> getFollowers(@RequestParam Integer userId) {
        return followRelationRepository.getFollowersQuery(userId);
    }

    @GetMapping(path="/following")
    public @ResponseBody Iterable<User> getFollowing(@RequestParam Integer userId) {
        return followRelationRepository.getFolloweesQuery(userId);
    }
}