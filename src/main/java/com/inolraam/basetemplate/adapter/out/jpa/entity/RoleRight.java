package com.inolraam.basetemplate.adapter.out.jpa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles_rights")
public class RoleRight extends AuditableEntity {
    @EmbeddedId
    private RoleRightPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", nullable = false, insertable = false, updatable = false)
    private Role idRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_right", nullable = false, insertable = false, updatable = false)
    private RightEntity idRight;
}
