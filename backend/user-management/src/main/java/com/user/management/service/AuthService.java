package com.user.management.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.exception.BaddAuthException;
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
            throw new BaddAuthException("error.loginNameOrEmail.invalid", "#003");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BaddAuthException("error.password.invalid", "#004");
        }
        return user;
    }

    private void validateUserParam(String loginName, String email, String password) {

        if (loginName == null && email == null) {
            throw new FieldException("error.parameter.emailOrLoginName.invalid", "#001",
                            "email or loginName");
        }
        if (password == null) {
            throw new FieldException("error.parameter.password.invalid", "#002",
                            "Password");
        }
    }

}
