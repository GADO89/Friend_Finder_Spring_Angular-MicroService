package com.user.management.service.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.exceptions.BadAuthException;
import com.user.management.exceptions.FieldException;
import com.user.management.model.dto.auth.AuthDto;
import com.user.management.model.user.User;
import com.user.management.repository.user.UserRepository;
import com.user.management.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                    UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /*
     * login with user
     *@param
     * @return AuthDto
     *
     */
    @Override
    public AuthDto authUser(Map<String, Object> params) {
        String loginName = (String) params.get("loginName");
        String email = (String) params.get("email");
        String password = (String) params.get("password");

        //  validate param of user
        validateUserParam(loginName, email, password);

        //  validate user auth
        User user = validateUserAuth(loginName, email, password);

        return new AuthDto(user.getId(), "token", "expire", "re_token", user.getRoles(),
                        user.isAdmin(), user.getScope());
    }

    /*  validate user auth
     * login with user
     *@param
     * @return AuthDto
     *
     */
    private User validateUserAuth(String loginName, String email, String password) {

        Optional<User> user =
                        (loginName != null) ? userRepository.findByLoginName(loginName)
                                        : userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new BadAuthException("error.loginNameOrEmail.invalid", "#003");
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new BadAuthException("error.password.invalid", "#004");
        }
        return user.get();
    }

    /*  validate user param
     * login with user
     *@param
     * @return AuthDto
     *
     */
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
