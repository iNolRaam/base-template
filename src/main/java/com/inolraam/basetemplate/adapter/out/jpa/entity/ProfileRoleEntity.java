package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "profiles_roles")
@EqualsAndHashCode(callSuper = false)
public class ProfileRoleEntity extends AuditableEntity {
    @EmbeddedId
    private ProfileRolePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false, insertable = false, updatable = false)
    private ProfileEntity idProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", nullable = false, insertable = false, updatable = false)
    private RoleEntity idRole;
}