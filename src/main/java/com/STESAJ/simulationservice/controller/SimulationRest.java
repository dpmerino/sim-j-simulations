package com.STESAJ.simulationservice.controller;

import com.STESAJ.simulationservice.entity.Simulation;
import com.STESAJ.simulationservice.service.SimulationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/simulations")
@CrossOrigin(origins = "*")
public class SimulationRest {

    @Autowired
    SimulationService simulationService;
    @GetMapping
    public ResponseEntity<List<Simulation>> listAllSimulations() {
        List<Simulation> simulations = simulationService.findSimulationAll();
        if (simulations.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(simulations);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Simulation> getSimulation(@PathVariable("id") long id) {
        log.info("Fetching Simulation with id {}", id);
        Simulation simulation  = simulationService.getSimulation(id);
        if (null == simulation) {
            log.error("Simulation with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(simulation);
    }

    @PostMapping
    public ResponseEntity<Simulation> createSimulation(@Valid @RequestBody Simulation simulation, BindingResult result) {
        log.info("Creating Simulation : {}", simulation);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Simulation simulationDB = simulationService.createSimulation (simulation);

        return  ResponseEntity.status( HttpStatus.CREATED).body(simulationDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Simulation> deleteInvoice(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Simulation with id {}", id);

        Simulation simulation = simulationService.getSimulation(id);
        if (simulation == null) {
            log.error("Unable to delete. Simulation with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        simulation = simulationService.deleteSimulation(simulation);
        return ResponseEntity.ok(simulation);
    }



    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
