package com.hchencs.socialnetworkproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String password;
    private String firstName;
    private String lastName;
    private DateTime dateOfBirth;
    private String location;
    private String email;

//    @OneToMany
//    @JoinColumn(name = "user_id")//, referencedColumnName = "id")
//    private Set<Post> posts;
}
