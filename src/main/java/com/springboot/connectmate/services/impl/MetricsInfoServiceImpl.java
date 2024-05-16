package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.models.MetricsInfo;
import com.springboot.connectmate.models.ThresholdBreaches;
import com.springboot.connectmate.repositories.MetricsInfoRepository;
import com.springboot.connectmate.repositories.ThresholdBreachesRepository;
import com.springboot.connectmate.services.MetricsInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetricsInfoServiceImpl implements MetricsInfoService {
    private final ModelMapper mapper;
    private final MetricsInfoRepository metricsInfoRepository;

    private final ThresholdBreachesRepository thresholdBreachesRepository;

    @Autowired
    public MetricsInfoServiceImpl(MetricsInfoRepository metricsInfoRepository, ThresholdBreachesRepository thresholdBreachesRepository,
                                  ModelMapper mapper
    ) {
        this.metricsInfoRepository = metricsInfoRepository;
        this.thresholdBreachesRepository = thresholdBreachesRepository;
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

    public void addThresholdBreaches(int id, ThresholdBreaches thresholdBreaches) {
        MetricsInfo metricsInfo = findById((long) id);
        if (metricsInfo != null) {
            thresholdBreaches.setMetricsInfoId(metricsInfo);
            thresholdBreachesRepository.save(thresholdBreaches);
        }
    }
}
