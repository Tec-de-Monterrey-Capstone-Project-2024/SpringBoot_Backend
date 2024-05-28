package com.springboot.connectmate.config;

import com.springboot.connectmate.enums.ConnectMetricCode;
import com.springboot.connectmate.models.Metric;
import com.springboot.connectmate.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private MetricRepository metricRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------------------");
        System.out.println("Inserting metrics into the database...");
        for (ConnectMetricCode code : ConnectMetricCode.values()) {
            if (!metricRepository.existsById(code)) {
                System.out.println("Inserting metric: " + code);
                Metric metric = new Metric();
                metric.setCode(code);
                metric.setMinimumThresholdValue(null);
                metric.setMaximumThresholdValue(null);
                metric.setTargetValue(code.getDefaultTargetValue());
                metricRepository.save(metric);
            } else {
                System.out.println("Metric already exists: " + code);
            }
        }
        System.out.println("---------------------------------");
    }
}
