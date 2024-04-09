package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.SupervisorDTO;

import java.util.List;
import java.util.Map;

public interface SupervisorService {
    SupervisorDTO createSupervisor(SupervisorDTO supervisorDTO);
    List<SupervisorDTO> getAllSupervisors();
    SupervisorDTO getSupervisorById(long id);
    SupervisorDTO updateSupervisor(long id, SupervisorDTO supervisorDTO);
    SupervisorDTO patchSupervisor(long id, Map<String, Object> fields);
    void deleteSupervisor(long id);
    List<SupervisorDTO> getAvailableSupervisors();
}
