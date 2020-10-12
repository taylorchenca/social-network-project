package com.hchencs.socialnetworkproject.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface FollowRelationRepository extends CrudRepository<FollowRelation, Integer> {

    @Query("select f.followee from FollowRelation f where f.follower.id = ?1 order by f.followee.id")
    Collection<User> getFolloweesQuery(Integer userId);

    @Query("select f.follower from FollowRelation f where f.followee.id = ?1 order by f.follower.id")
    Collection<User> getFollowersQuery(Integer userId);
}
