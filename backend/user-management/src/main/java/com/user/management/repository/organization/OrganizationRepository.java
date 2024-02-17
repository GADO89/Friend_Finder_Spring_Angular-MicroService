package com.user.management.repository.organization;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.model.organization.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    /*
     * find user By Reference_Id
     * @param reference Id
     * @return User
     *
     */
    Optional<Organization> findByReferencerId(String referenceId);
}
