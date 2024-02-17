package com.user.management.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.user.management.model.dto.auth.AuthDto;

@Service
public interface AuthService {

    /*
    * login with user
    *@param
    * @return AuthDto
    *
    */
    public AuthDto authUser(Map<String, Object> params);

}
