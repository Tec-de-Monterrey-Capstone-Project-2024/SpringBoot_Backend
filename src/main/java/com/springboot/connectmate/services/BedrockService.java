package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightFieldsDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.KPIDataContextDTO;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.ThresholdBreachFieldsDTO;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.models.Metric;
import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;

public interface BedrockService {

    void generateInsight(
            Metric metric,
            ThresholdBreachFieldsDTO thresholdBreachFields,
            String typeName
    );
    String generate(String message);
    Flux<ChatResponse> generateStream(String message);
    InsightFieldsDTO createInsight(KPIDataContextDTO dataContext);

}
