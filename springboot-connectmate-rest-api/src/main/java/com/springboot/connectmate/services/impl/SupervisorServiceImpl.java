package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.SupervisorDTO;
import com.springboot.connectmate.exceptions.ResourceNotFoundException;
import com.springboot.connectmate.models.Supervisor;
import com.springboot.connectmate.services.SupervisorService;
import com.springboot.connectmate.repositories.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService{

    private final SupervisorRepository supervisorRepository;

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
        return mapToDTO(newSupervisor);
    }

    @Override
    public List<SupervisorDTO> getAllSupervisors() {
        // Get all Supervisors
        List<Supervisor> supervisors = supervisorRepository.findAll();
        // this lambda expression is more readable
        //return supervisors.stream().map(supervisor -> mapToDTO(supervisor)).collect(Collectors.toList());
        // this method reference is more concise, both do the same thing
        return supervisors.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public SupervisorDTO getSupervisorById(long id) {
        // Get Supervisor by ID
        Supervisor supervisor = supervisorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));
        return mapToDTO(supervisor);
    }

    @Override
    public SupervisorDTO updateSupervisor(long id, SupervisorDTO supervisorDTO) {
        // Get Supervisor by ID
        Supervisor supervisor = supervisorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

        // Update Supervisor
        supervisor.setName(supervisorDTO.getName());
        supervisor.setEmail(supervisorDTO.getEmail());
        supervisor.setPassword(supervisorDTO.getPassword());
        supervisor.setPhone(supervisorDTO.getPhone());
        supervisor.setAddress(supervisorDTO.getAddress());
        supervisor.setInstanceId(supervisorDTO.getInstanceId());

        // Save Supervisor
        Supervisor updatedSupervisor = supervisorRepository.save(supervisor);

        // Convert Model To DTO
        return mapToDTO(updatedSupervisor);

    }

    @Override
    public SupervisorDTO patchSupervisor(long id, Map<String, Object> fields) {
        // Get Supervisor by ID
        Supervisor supervisor = supervisorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

        fields.forEach((key, value) -> {
            // Map fields to properties of Supervisor
            switch (key) {
                case "name":
                    supervisor.setName((String) value);
                    break;
                case "email":
                    supervisor.setEmail((String) value);
                    break;
                case "password":
                    supervisor.setPassword((String) value);
                    break;
                case "phone":
                    supervisor.setPhone((String) value);
                    break;
                case "address":
                    supervisor.setAddress((String) value);
                    break;
                case "instanceId":
                    supervisor.setInstanceId((String) value);
                    break;
            }
        });

        Supervisor updatedSupervisor = supervisorRepository.save(supervisor);

        // Convert to DTO and return (assuming there's a method to convert an entity to DTO)
        return mapToDTO(updatedSupervisor);
    }

    @Override
    public void deleteSupervisor(long id) {
        // Get Supervisor by ID
        Supervisor supervisor = supervisorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

        // Delete Supervisor
        supervisorRepository.delete(supervisor);

    }

}
