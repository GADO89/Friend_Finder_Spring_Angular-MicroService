package com.user.management.model.role;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.user.management.model.BaseEntity;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Table
public class Role extends BaseEntity {

    private String code;

    private String displayName;
}
