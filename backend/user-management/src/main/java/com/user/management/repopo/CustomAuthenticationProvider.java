package com.user.management.repopo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.user.management.model.organization.Organization;
import com.user.management.model.organizationRole.OrganizationRole;
import com.user.management.repopo.organization.OrganizationRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private OrganizationRepository organizationRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(OrganizationRepository organizationRepository,
                    PasswordEncoder passwordEncoder) {
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
                    throws AuthenticationException {
        String referenceId = authentication.getName(); // true
        String password = authentication.getCredentials().toString();
        Optional<Organization> organization =
                        organizationRepository.findByReferencerId(referenceId);

        if (organization.isPresent()) {
            if (passwordEncoder.matches(password, organization.get().getPassword())) {
                //                List<SimpleGrantedAuthority> organizationRoles =
                //                                organization.get().getRoles().stream().map(role -> {
                //                                    return new SimpleGrantedAuthority(
                //                                                    role.getRole().getCode());
                //
                //                                }).collect(Collectors.toList());
                return new UsernamePasswordAuthenticationToken(referenceId, password,
                                toSimpleGrantedAuthority(organization.get().getRoles()));
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<SimpleGrantedAuthority> toSimpleGrantedAuthority(
                    List<OrganizationRole> organizationRoles) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (OrganizationRole organizationRole : organizationRoles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(
                            organizationRole.getRole().getCode()));
        }
        return simpleGrantedAuthorities;
    }
}
