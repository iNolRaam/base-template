package com.inolraam.basetemplate.adapter.out.jpa.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "rights")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RightEntity extends BaseCatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rights_id")
    @SequenceGenerator(name = "seq_rights_id", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_right")
    private TypeRight idTypeRight;
}
