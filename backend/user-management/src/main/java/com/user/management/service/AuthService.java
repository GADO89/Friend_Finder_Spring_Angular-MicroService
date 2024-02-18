package com.user.management.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.user.management.model.dto.auth.OrgAuthDto;
import com.user.management.model.dto.auth.UserAuthDto;

@Service
public interface AuthService {

    /*
    * login with user
    * @param params
    * @return UserAuthDto
    *
    */
    UserAuthDto authUser(Map<String, Object> params);
    /*
     * login with organization
     * @param  params
     * @return OrgAuthDto
     *
     */

    OrgAuthDto authOrganization(Map<String, Object> params);

}
