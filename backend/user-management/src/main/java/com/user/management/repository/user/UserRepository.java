package com.user.management.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*
     * find user by login Name
     * @param loginName
     * @return User
     *
     */
    Optional<User> findByLoginName(String loginName);

    /*
     * find user by email
     * @param email
     * @return User
     *
     */
    Optional<User> findByEmail(String email);

}
