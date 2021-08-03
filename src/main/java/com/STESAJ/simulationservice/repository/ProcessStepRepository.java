package com.STESAJ.simulationservice.repository;

import com.STESAJ.simulationservice.entity.ProcessStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStepRepository extends JpaRepository<ProcessStep, Long> {

}
