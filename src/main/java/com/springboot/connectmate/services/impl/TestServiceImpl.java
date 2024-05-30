package com.springboot.connectmate.services.impl;
import com.amazonaws.services.connect.model.Statistic;
import com.amazonaws.services.connect.model.Unit;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.KPIDataContextDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.Status;
import com.springboot.connectmate.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Random;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    private final Random random = new Random();

    public void createMultipleRecords() {
        String baseUrl = "http://localhost:8080/api/threshold-breach-insights/generateAndSaveInsight";

        for (long i = 1; i <= 50; i++) {
            KPIDataContextDTO KPIDataContextDTO = generateRandomKpiDataDTO(i);

            double metricValue = (double) random.nextInt(1000);

            sendRequest(baseUrl, metricValue, randomEnum(ConnectMetricType.class).name(),
                    String.valueOf(random.nextInt(1000)), randomEnum(ConnectMetricCode.class).name(),
                    randomEnum(Status.class).name(), KPIDataContextDTO);
        }
    }

    private KPIDataContextDTO generateRandomKpiDataDTO(Long id) {
        KPIDataContextDTO dto = new KPIDataContextDTO();
        dto.setId(id);
        dto.setMetric(randomEnum(ConnectMetricCode.class).name());
        dto.setDescription("Randomly generated metric description.");
        dto.setHasPositiveUpside(random.nextBoolean());
        dto.setBelongsToUser(random.nextBoolean());
        dto.setBelongsToQueue(random.nextBoolean());
        dto.setUnit(randomEnum(Unit.class).name());
        dto.setStatistic(randomEnum(Statistic.class).name());
        dto.setMinimumThresholdValue(random.nextDouble(50) + 50);
        dto.setMaximumThresholdValue(random.nextDouble(50) + 100);
        dto.setTargetValue(random.nextDouble(50) + 75);
        dto.setCurrentValue(random.nextDouble(50) + 60);
        dto.setBreachOccurred(random.nextBoolean());
        return dto;
    }

    private <T extends Enum<?>> T randomEnum(Class<T> enumClass) {
        int x = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[x];
    }

    private void sendRequest(String url, Double metricValue, String metricType, String typeId, String metricCode, String status, KPIDataContextDTO KPIDataContextDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<KPIDataContextDTO> entity = new HttpEntity<>(KPIDataContextDTO, headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("metricValue", metricValue)
                .queryParam("metricType", metricType)
                .queryParam("typeId", typeId)
                .queryParam("metricCode", metricCode)
                .queryParam("status", status);

        String finalUrl = uriBuilder.toUriString();

        restTemplate.postForObject(finalUrl, entity, String.class);
    }

}