package com.user.management.model.organization;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import com.user.management.model.BaseEntity;
import com.user.management.model.enums.Scope;
import com.user.management.model.organizationRole.OrganizationRole;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "organization")
public class Organization extends BaseEntity {

    @Column(name = "referencer_id")
    private String referencerId;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "password")
    private String password;

    @Column(name = "scope")
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization")
    private List<OrganizationRole> roles;

}
