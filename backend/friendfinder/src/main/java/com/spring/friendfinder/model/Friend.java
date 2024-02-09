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
@Entity(name = "friend")
public class Friend extends BaseEntity{

    @Column(name = "friend_id")
        private int idFriend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
