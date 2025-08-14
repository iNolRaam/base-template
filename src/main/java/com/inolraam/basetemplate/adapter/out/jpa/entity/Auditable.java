package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Auditable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Column(name = "created_by", nullable = false, length = 100)
    @Size(max = 100)
    protected String createdBy;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    protected Date created;

    @Column(name = "updated_by", length = 100)
    @Size(max = 100)
    protected String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    protected Date lastUpdated;
}
