package com.inolraam.basetemplate.adapter.out.jpa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "type_rights")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeRight extends BaseCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_rights_id")
    @SequenceGenerator(name = "seq_type_rights_id", allocationSize = 1)
    private long id;
}
