package com.inolraam.basetemplate.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    protected String createdBy;
    protected String updatedBy;
    protected Date lastUpdated;
    protected Date created;
}
