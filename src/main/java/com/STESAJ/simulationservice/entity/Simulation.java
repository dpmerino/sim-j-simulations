package com.STESAJ.simulationservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "simulations")
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private long simulation_id ;

    @NotEmpty(message = "El identificador no puede ser vacío")
    private String identifier;

    @NotEmpty(message = "El nombre no puede ser vacío")
    private String name_simulation;

    @NotEmpty(message = "La descripcion no puede ser vacía")
    private String desc_simulation;

    @NotEmpty(message = "El campo materia no puede ser vacío")
    private String subject;

    @Column(name = "action_id")
    private Long actionId;


    private String status;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "p_steps_id")
    @Embedded
    private List<ProcessStep> steps;

    public Simulation(){
        steps = new ArrayList<>();
    }

}
