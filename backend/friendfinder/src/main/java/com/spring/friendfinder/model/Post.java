package com.spring.friendfinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post")
public class Post extends BaseEntity{


    @Column(name = "image_path")
    private String image;
    @Column(name = "text")
    private String text;
    @Column(name = "likes")
    private int like;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
