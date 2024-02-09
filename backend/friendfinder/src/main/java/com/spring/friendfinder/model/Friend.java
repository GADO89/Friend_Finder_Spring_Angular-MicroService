package com.spring.friendfinder.model;

import jakarta.persistence.*;

@Entity(name = "friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "friend_id")
        private int idFriend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
