package com.user.management.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.exception.FieldException;
import com.user.management.model.dto.auth.AuthDto;
import com.user.management.model.user.User;
import com.user.management.user.UserRepository;

@Service
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public AuthDto authUser(Map<String, Object> params) {
        String loginName = (String) params.get("loginName");
        String email = (String) params.get("email");
        String password = (String) params.get("password");
        validateUserParam(loginName, email, password);
        User user = validateUserAuth(loginName, email, password);

        return new AuthDto(user.getId(), "token", "expire", "re_token", user.getRoles(),
                        user.isAdmin(), user.getScope());
    }

    private User validateUserAuth(String loginName, String email, String password) {

        User user = (loginName != null) ? userRepository.findByLoginName(loginName)
                        : userRepository.findByEmail(email);
        if (user == null) {
            throw new BadCredentialsException("Invalid loginName or email");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return user;
    }

    private void validateUserParam(String loginName, String email, String password) {

        if (loginName == null && email == null) {
            throw new FieldException(
                            "Invalid Parameter: you must enter an email or login name",
                            "#001", "email or loginName");
        }
        if (password == null) {
            throw new FieldException("Invalid Parameter: you must enter a password",
                            "#002", "Password");
        }
    }

}
