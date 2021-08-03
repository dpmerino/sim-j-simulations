package com.STESAJ.simulationservice.service;

import com.STESAJ.simulationservice.entity.Simulation;

import java.util.List;

public interface SimulationService {
    public List<Simulation> findSimulationAll();
    public Simulation createSimulation(Simulation simulation);
    public Simulation deleteSimulation(Simulation simulation);

    public Simulation getSimulation(Long id);
}
