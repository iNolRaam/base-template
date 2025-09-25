package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedBy
    @NotBlank
    @Column(name = "created_by", nullable = false, length = 100, updatable = false)
    @Size(min= 1, max = 100)
    private String createdBy = "Yomero"; //TODO remove default value

    @CreatedDate
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "updated_by", length = 100, insertable = false)
    @Size(max = 100)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "lst_updated_at", insertable = false)
    private LocalDateTime lastUpdatedAt;
}
