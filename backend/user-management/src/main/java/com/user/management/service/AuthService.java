package com.user.management.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private AuthDto toAuthDto(User user) {
        AuthDto authDto = new AuthDto();
        authDto.setUserId(user.getId());
        authDto.setAcces_token("token");
        authDto.setAdmin(user.isAdmin());
        authDto.setRefreshToken("re_token");
        authDto.setRoles(user.getRoles());
        authDto.setExpireAt("expire");
        authDto.setScope(user.getScope());

        return authDto;
    }

    private void validateUserParam(String loginName, String email, String password) {

        if (loginName == null && email == null) {
            throw new BadCredentialsException(
                            "You must enter a valid email or login name");
        }
        if (password == null) {
            throw new BadCredentialsException("You must enter password");
        }
    }

}
