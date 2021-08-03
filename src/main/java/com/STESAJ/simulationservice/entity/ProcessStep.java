package com.STESAJ.simulationservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "p_steps")
@Embeddable
public class ProcessStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private long p_steps_id;

    @NotEmpty(message = "El nombre no puede ser vacío")
    private String name;

    @NotEmpty(message = "El campo ayuda no puede ser vacío")
    private String help;

    @NotEmpty(message = "La descripcion no puede ser vacía")
    private String description;
}
