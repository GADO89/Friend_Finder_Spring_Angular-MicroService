package com.spring.friendfinder.model;

import com.spring.friendfinder.model.enums.Gender;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "user")
public class User extends BaseEntity{


    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "last_Name")
    private String lastName;
    @Column(name = "age")
    private String age;
    @Column(name = "phone")
    private String phone;
    @Column(name = "date")
    private Date date;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    private List<Friend> friends;


}
