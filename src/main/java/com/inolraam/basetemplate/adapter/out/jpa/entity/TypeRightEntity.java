package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "type_rights")
public class TypeRightEntity extends BaseCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_rights_id")
    @SequenceGenerator(name = "seq_type_rights_id", allocationSize = 1)
    private Long id;

    public TypeRightEntity(long id){
        this.id = id;
    }
}
