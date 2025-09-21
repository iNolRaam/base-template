package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles_rights")
@EqualsAndHashCode(callSuper = false)
public class RoleRightEntity extends AuditableEntity {
    @EmbeddedId
    private RoleRightPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", nullable = false, insertable = false, updatable = false)
    private RoleEntity idRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_right", nullable = false, insertable = false, updatable = false)
    private RightEntity idRight;
}
