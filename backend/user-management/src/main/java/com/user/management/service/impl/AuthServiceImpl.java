package com.user.management.service.impl;

import javax.transaction.SystemException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.management.exceptions.BadAuthException;
import com.user.management.exceptions.FieldException;
import com.user.management.model.dto.auth.OrgAuthDto;
import com.user.management.model.dto.auth.UserAuthDto;
import com.user.management.model.dto.role.RoleDto;
import com.user.management.model.organization.Organization;
import com.user.management.model.organizationRole.OrganizationRole;
import com.user.management.model.user.User;
import com.user.management.repository.organization.OrganizationRepository;
import com.user.management.repository.user.UserRepository;
import com.user.management.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    private OrganizationRepository organizationRepository;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                    OrganizationRepository organizationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    /*
     * login with user
     *@param
     * @return AuthDto
     *
     */
    @Override
    public UserAuthDto authUser(Map<String, Object> params) throws SystemException {
        String loginName = (String) params.get("loginName");
        String email = (String) params.get("email");
        String password = (String) params.get("password");

        //  validate param of user
        validateUserParam(loginName, email, password);

        //  validate user auth
        User user = validateUserAuth(loginName, email, password);

        return new UserAuthDto(user.getId(), "token", "expire", "re_token",
                        extractRoles(user), user.isAdmin(), user.getScope());
    }

    /*
     * login with organization
     * @param  params
     * @return OrgAuthDto
     *
     */
    @Override
    public OrgAuthDto authOrganization(Map<String, Object> params)
                    throws SystemException {
        //extract validate params of the organization
        String referencerId = (String) params.get("referencer_id");
        String password = (String) params.get("password");

        //  validate params of the Organization
        validateOrganizationParam(referencerId, password);

        //  validate Organization auth
        Organization organization = validateOrganizationAuth(referencerId, password);

        return new OrgAuthDto(organization.getId(), "token", "expire", "re_token",
                        extractRoles(organization), organization.getScope());
    }

    private Organization validateOrganizationAuth(String referencerId, String password) {

        Optional<Organization> organization =
                        organizationRepository.findByReferencerId(referencerId);
        if (!organization.isPresent()) {
            throw new BadAuthException("error.referencerId.invalid", "#007");
        }
        if (!passwordEncoder.matches(password, organization.get().getPassword())) {
            throw new BadAuthException("error.password.invalid", "#008");
        }
        return organization.get();
    }

    /*  validate Organization Param
     * login with user
     * @param
     *  @return AuthDto
     */
    private void validateOrganizationParam(String referencerId, String password) {
        if (referencerId == null) {
            throw new FieldException("error.parameter.referencerId.invalid", "#005",
                            " referencerId");
        }
        if (password == null) {
            throw new FieldException("error.parameter.password.invalid", "#006",
                            "Password");
        }
    }

    /*  validate user auth
     * login with user
     *@param
     * @return AuthDto
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
    /*
     * extract roles
     *@param roles
     *
     *
     */

    private List<RoleDto> extractOrganizationRole(List<OrganizationRole> roles) {
        return roles.stream()
                        .map(organizationRole -> new RoleDto(
                                        organizationRole.getRole().getCode(),
                                        organizationRole.getRole().getDisplayName()))
                        .collect(Collectors.toList());
    }
    /*
     * extract roles
     *@param roles
     *
     *
     */

    private <T> List<RoleDto> extractRoles(T userType) throws SystemException {

        if (!(userType instanceof User || userType instanceof Organization)) {
            throw new SystemException(
                            "to extract roles you must send User Or Organization ");
        }

        if (userType instanceof User) {
            return ((User) userType).getRoles().stream()
                            .map(organizationRole -> new RoleDto(
                                            organizationRole.getRole().getCode(),
                                            organizationRole.getRole().getDisplayName()))
                            .collect(Collectors.toList());

        }
        if (userType instanceof Organization) {

            return ((Organization) userType).getRoles().stream()
                            .map(organizationRole -> new RoleDto(
                                            organizationRole.getRole().getCode(),
                                            organizationRole.getRole().getDisplayName()))
                            .collect(Collectors.toList());
        }
        return null;
    }
}
