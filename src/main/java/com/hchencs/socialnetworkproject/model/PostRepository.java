package com.hchencs.socialnetworkproject.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("select p from Post p where p.user.id = ?1 order by p.publishDate desc")
    Collection<Post> getPostsByUserIdQuery(Integer userId);
}

