package com.user.management.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginName(String loginName);

    User findByEmail(String email);

}
