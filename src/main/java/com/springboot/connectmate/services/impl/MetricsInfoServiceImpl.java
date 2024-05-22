package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.Metric.ThresholdBreachesRequestDTO;
import com.springboot.connectmate.models.MetricsInfo;
import com.springboot.connectmate.models.ThresholdBreaches;
import com.springboot.connectmate.models.Users;
import com.springboot.connectmate.repositories.MetricsInfoRepository;
import com.springboot.connectmate.repositories.ThresholdBreachesRepository;
import com.springboot.connectmate.services.MetricsInfoService;
import com.springboot.connectmate.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetricsInfoServiceImpl implements MetricsInfoService {
    private final ModelMapper mapper;
    private final MetricsInfoRepository metricsInfoRepository;
    private final ThresholdBreachesRepository thresholdBreachesRepository;
    private final UsersService usersService;

    @Autowired
    public MetricsInfoServiceImpl(MetricsInfoRepository metricsInfoRepository,
                                  ThresholdBreachesRepository thresholdBreachesRepository,
                                  UsersService usersService,
                                  ModelMapper mapper
    ) {
        this.metricsInfoRepository = metricsInfoRepository;
        this.thresholdBreachesRepository = thresholdBreachesRepository;
        this.usersService = usersService;
        this.mapper = mapper;
    }

    @Override
    public MetricsInfo findById(Long id) {
        Optional<MetricsInfo> metricsInfoOptional = metricsInfoRepository.findById(id);
        if (metricsInfoOptional.isPresent()) {
            return metricsInfoOptional.get();
        } else {
            throw new RuntimeException("MetricsInfo not found for id: " + id);
        }
    }

    public void addThresholdBreaches(ThresholdBreachesRequestDTO thresholdBreaches) {
        MetricsInfo metricsInfo = findById(thresholdBreaches.getMetricsInfoId());
        Users agent = usersService.findById(thresholdBreaches.getAgentConnectId());

        // DTO -> Model
        ThresholdBreaches thresholdBreachesModel = new ThresholdBreaches();

        thresholdBreachesModel.setMetricsInfoId(metricsInfo);
        thresholdBreachesModel.setAgentId(agent);
        thresholdBreachesModel.setQueueId(thresholdBreaches.getQueueId());
        thresholdBreachesModel.setValue(thresholdBreaches.getValue());
        thresholdBreachesModel.setPerformance(thresholdBreaches.getPerformance());
        thresholdBreachesModel.setOccurredAt(thresholdBreaches.getOccurredAt());
        thresholdBreachesModel.setStatus(thresholdBreaches.getStatus());

        if (metricsInfo != null) {
            thresholdBreachesRepository.save(thresholdBreachesModel);
        }
    }
}
