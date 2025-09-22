package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
public class RoleEntity extends BaseCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_roles_id")
    @SequenceGenerator(name = "seq_roles_id", allocationSize = 1)
    private Long id;

    @ElementCollection
    @CollectionTable(
        name = "roles_rights",
        joinColumns = @JoinColumn(name = "id_role")
    )
    @Column(name = "id_right")
    private Set<Long> rights;

    public RoleEntity(long id) {
        this.id = id;
    }
}
