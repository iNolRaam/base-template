package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "profiles")
public class ProfileEntity extends BaseCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_profiles_id")
    @SequenceGenerator(name = "seq_profiles_id", allocationSize = 1)
    private long id;

    @ElementCollection
    @CollectionTable(
            name = "profiles_roles",
            joinColumns = @JoinColumn(name = "id_profile")
    )
    @Column(name = "id_role")
    private Set<Long> roles;
}
