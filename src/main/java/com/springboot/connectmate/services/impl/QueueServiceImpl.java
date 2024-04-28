package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.repositories.QueueRepository;
import com.springboot.connectmate.services.QueueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    private final ModelMapper mapper;
    private final QueueRepository queueRepository;

    @Autowired
    public QueueServiceImpl(QueueRepository queueRepository, ModelMapper mapper) {
        this.queueRepository = queueRepository;
        this.mapper = mapper;
    }
}
