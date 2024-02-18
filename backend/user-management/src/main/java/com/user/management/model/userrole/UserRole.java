package com.user.management.model.userrole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

import com.user.management.model.role.CompositeKey;
import com.user.management.model.role.Role;
import com.user.management.model.user.User;

@Getter
@Setter
@Entity
@Table(name = "user_role")
@NoArgsConstructor
public class UserRole implements Serializable {

    @EmbeddedId
    private CompositeKey userRoleKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("integrationId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @MapsId("roleId")
    private Role role;

}
