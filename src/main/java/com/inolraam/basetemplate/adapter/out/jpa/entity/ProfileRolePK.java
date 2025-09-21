package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRolePK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id_profile", insertable = false, updatable = false, nullable = false)
    private long idProfile;

    @Column(name = "id_role", insertable = false, updatable = false, nullable = false)
    private long idRole;
}