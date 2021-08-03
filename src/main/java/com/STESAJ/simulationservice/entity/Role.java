package com.STESAJ.simulationservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private long role_id;

    @NotEmpty(message = "El nombre del rol no puede ser vacío")
    private String name_role;
}
