package com.STESAJ.simulationservice.service;

import com.STESAJ.simulationservice.entity.ProcessStep;
import com.STESAJ.simulationservice.entity.Simulation;
import com.STESAJ.simulationservice.repository.ProcessStepRepository;
import com.STESAJ.simulationservice.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationServiceImpl implements SimulationService{

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    ProcessStepRepository processStepRepository;

    @Override
    public List<Simulation> findSimulationAll() {

        return simulationRepository.findAll();
    }

    @Override
    public Simulation createSimulation(Simulation simulation) {

        Simulation simulationDB = simulationRepository.findByIdentifier(simulation.getIdentifier());
        List<ProcessStep> steps = processStepRepository.findAll();
        if (simulationDB !=null){
            return  simulationDB;
        }
        simulation.setSteps(steps);
        simulation.setStatus("CREATED");
        return simulationRepository.save(simulation);
    }

    @Override
    public Simulation deleteSimulation(Simulation simulation) {
        Simulation simulationDB = getSimulation(simulation.getSimulation_id());
        if (simulationDB == null){
            return  null;
        }
        simulationDB.setStatus("DELETED");
        return simulationRepository.save(simulationDB);
    }

    @Override
    public Simulation getSimulation(Long id) {
        return simulationRepository.findById(id).orElse(null);
    }
}
