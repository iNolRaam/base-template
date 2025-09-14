package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "rights")
@EqualsAndHashCode(callSuper = true)
public class RightEntity extends BaseCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rights_id")
    @SequenceGenerator(name = "seq_rights_id", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_right")
    private TypeRightEntity idTypeRight;

    public RightEntity(long id) {
        this.id = id;
    }
}
