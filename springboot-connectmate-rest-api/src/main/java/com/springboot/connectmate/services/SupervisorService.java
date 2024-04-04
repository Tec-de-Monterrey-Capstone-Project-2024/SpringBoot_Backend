package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.SupervisorDTO;

import java.util.List;

public interface SupervisorService {
    SupervisorDTO createSupervisor(SupervisorDTO supervisorDTO);
    List<SupervisorDTO> getAllSupervisors();
    SupervisorDTO getSupervisorById(long id);
    SupervisorDTO updateSupervisor(long id, SupervisorDTO supervisorDTO);
    void deleteSupervisor(long id);
}
