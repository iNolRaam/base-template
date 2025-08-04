package com.inolraam.basetemplate.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rights")
public class Right {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
