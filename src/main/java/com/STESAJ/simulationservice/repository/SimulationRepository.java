package com.STESAJ.simulationservice.repository;

import com.STESAJ.simulationservice.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    //public List<Simulation> findByActionId(Long actionId );
    public Simulation findByIdentifier(String identifier);

}
