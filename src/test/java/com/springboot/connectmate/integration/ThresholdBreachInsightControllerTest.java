package com.springboot.connectmate.integration;

import com.springboot.connectmate.controllers.ThresholdBreachInsightController;
import com.springboot.connectmate.dtos.ThresholdBreachInsight.InsightAlertDTO;
import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.enums.ConnectMetricType;
import com.springboot.connectmate.enums.InsightPerformance;
import com.springboot.connectmate.services.ThresholdBreachInsightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ThresholdBreachInsightControllerTest {

    @Mock
    private ThresholdBreachInsightService thresholdBreachInsightService;

    @InjectMocks
    private ThresholdBreachInsightController thresholdBreachInsightController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAlerts() {
        // Mocking the service response
        List<InsightAlertDTO> mockAlerts = new ArrayList<>();
        InsightAlertDTO alert1 = new InsightAlertDTO();
        alert1.setId(1L);
        alert1.setMetricCode(ConnectMetricCode.SERVICE_LEVEL);
        alert1.setInsightCategory(InsightPerformance.CRITICAL);
        alert1.setConnectItemType(ConnectMetricType.AGENT);
        alert1.setOccurredAt("Fri May 03 17:29:27 CST 2024");

        InsightAlertDTO alert2 = new InsightAlertDTO();
        alert2.setId(2L);
        alert2.setMetricCode(ConnectMetricCode.ABANDONMENT_RATE);
        alert2.setInsightCategory(InsightPerformance.UNSATISFACTORY);
        alert2.setConnectItemType(ConnectMetricType.QUEUE);
        alert2.setOccurredAt("Sat May 04 10:15:30 CST 2024");

        mockAlerts.add(alert1);
        mockAlerts.add(alert2);

        // Mock the service method call
        when(thresholdBreachInsightService.getAlerts()).thenReturn(mockAlerts);

        // Call the controller method
        ResponseEntity<List<InsightAlertDTO>> response = thresholdBreachInsightController.getAlerts();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(mockAlerts, response.getBody());
    }
}
