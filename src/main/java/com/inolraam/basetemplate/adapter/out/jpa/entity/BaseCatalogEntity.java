package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@MappedSuperclass
public class BaseCatalogEntity extends AuditableEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "boolean")
    private Boolean visible;
}