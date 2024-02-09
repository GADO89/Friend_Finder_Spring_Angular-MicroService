package com.spring.friendfinder.model;

import jakarta.persistence.*;
@MappedSuperclass
@Entity(name = "friend")
public class Friend extends BaseEntity{

    @Column(name = "friend_id")
        private int idFriend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
