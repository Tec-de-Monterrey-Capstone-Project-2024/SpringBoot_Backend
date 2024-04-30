package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.User.UserDTO;
import com.springboot.connectmate.enums.UserRole;
import com.springboot.connectmate.repositories.QueueRepository;
import com.springboot.connectmate.services.QueueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueueServiceImpl implements QueueService {

    private final ModelMapper mapper;
    private final QueueRepository queueRepository;

    @Autowired
    public QueueServiceImpl(QueueRepository queueRepository, ModelMapper mapper) {
        this.queueRepository = queueRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<UserDTO> getQueueAgents(Long queueId) {
        List<Object[]> results = queueRepository.getQueueAgents(queueId);
        return results.stream()
                .map(result -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(((Long) result[0]));
                    dto.setFirstName((String) result[1]);
                    dto.setLastName((String) result[2]);
                    dto.setUsername((String) result[3]);
                    dto.setEmail((String) result[4]);
                    dto.setPassword((String) result[5]);
                    dto.setRole(UserRole.valueOf((String) result[6]));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
