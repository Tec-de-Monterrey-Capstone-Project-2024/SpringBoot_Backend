package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.AmazonConnect.InsightDTO;
import com.springboot.connectmate.dtos.AmazonConnect.KpiDataDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.models.Metric;
import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;

public interface BedrockService {

    void generateInsight(
            Metric metric,
            Double metricValue,
            ConnectMetricType metricType,
            String typeId
    );
    String generate(String message);
    Flux<ChatResponse> generateStream(String message);
    InsightDTO createInsight(
            KpiDataDTO kpiDataDTO
            );

}
