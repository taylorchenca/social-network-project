package com.hchencs.socialnetworkproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)//TODO: Find out why id is being incremented in both User and Post
    private Integer id;

    @OneToOne
    private User user;

    @Column(columnDefinition = "TIMESTAMP")
    private Date publishDate;
    private String content;
}
