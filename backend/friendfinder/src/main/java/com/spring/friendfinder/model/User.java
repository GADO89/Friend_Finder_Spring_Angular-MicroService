package com.spring.friendfinder.model;

import com.spring.friendfinder.model.enums.Gender;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity(name = "user")
public class User extends BaseEntity{


    @Column(name = "id")
    private String firstName;
    @Column(name = "id")
    private String lastName;
    @Column(name = "id")
    private String age;
    @Column(name = "id")
    private String phone;
    @Column(name = "id")
    private Date date;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    private List<Friend> friends;


}
