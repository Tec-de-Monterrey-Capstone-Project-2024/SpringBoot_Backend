package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.SupervisorDTO;
import com.springboot.connectmate.models.Supervisor;
import com.springboot.connectmate.services.SupervisorService;
import com.springboot.connectmate.repositories.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService{

    private SupervisorRepository supervisorRepository;

    // No need for @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    // Convert Entity Model to DTO
    private SupervisorDTO mapToDTO(Supervisor supervisor) {
        SupervisorDTO supervisorDTO = new SupervisorDTO();
        supervisorDTO.setId(supervisor.getId());
        supervisorDTO.setName(supervisor.getName());
        supervisorDTO.setEmail(supervisor.getEmail());
        supervisorDTO.setPassword(supervisor.getPassword());
        supervisorDTO.setPhone(supervisor.getPhone());
        supervisorDTO.setAddress(supervisor.getAddress());
        supervisorDTO.setInstanceId(supervisor.getInstanceId());
        return supervisorDTO;
    }

    // Convert DTO to Entity Model
    private Supervisor mapToEntity(SupervisorDTO supervisorDTO) {
        Supervisor supervisor = new Supervisor();
        supervisor.setId(supervisorDTO.getId());
        supervisor.setName(supervisorDTO.getName());
        supervisor.setEmail(supervisorDTO.getEmail());
        supervisor.setPassword(supervisorDTO.getPassword());
        supervisor.setPhone(supervisorDTO.getPhone());
        supervisor.setAddress(supervisorDTO.getAddress());
        supervisor.setInstanceId(supervisorDTO.getInstanceId());

        return supervisor;
    }

    @Override
    public SupervisorDTO createSupervisor(SupervisorDTO supervisorDTO) {
        // Convert DTO to Model
        Supervisor supervisor = mapToEntity(supervisorDTO);

        // Save Model to DB
        Supervisor newSupervisor = supervisorRepository.save(supervisor);

        // Convert Model to DTO
        SupervisorDTO supervisorResponse = mapToDTO(newSupervisor);

        return supervisorResponse;
    }

    @Override
    public List<SupervisorDTO> getAllSupervisors() {

        List<Supervisor> supervisors = supervisorRepository.findAll();
        return supervisors.stream().map(supervisor -> mapToDTO(supervisor)).collect(Collectors.toList());
    }



}
