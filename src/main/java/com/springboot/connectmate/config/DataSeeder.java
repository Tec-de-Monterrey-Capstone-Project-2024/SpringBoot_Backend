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
        System.out.println("Inserting metrics into the database...");
        /*
        for (ConnectMetricCode code : ConnectMetricCode.values()) {
            if (!metricRepository.existsById(code)) {
                Metric metric = new Metric(code, null, null, null, null);
                metricRepository.save(metric);
            }
        }
        */

    }




}
