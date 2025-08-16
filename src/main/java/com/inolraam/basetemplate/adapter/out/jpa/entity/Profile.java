package com.inolraam.basetemplate.adapter.out.jpa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "profiles")
public class Profile extends BaseCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_profiles_id")
    @SequenceGenerator(name = "seq_profiles_id", allocationSize = 1)
    private long id;
}
