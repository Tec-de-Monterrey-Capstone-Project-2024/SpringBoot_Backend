package com.springboot.connectmate.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseField {
    NAME("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight name. This field should contain a concise name for the insight. KPI Data:"),
    SUMMARY("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight summary and return it. This field should contain a brief summary of the insight. KPI Data:"),
    DESCRIPTION("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight description and return it. This field should contain a detailed description of the insight. KPI Data:"),
    ACTION("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight actions and return it. This field should contain recommended actions to address the threshold breach. KPI Data:"),
    CATEGORY("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight category and return it. This field should only contain the severity level of the threshold breach (LOW, MEDIUM, HIGH). KPI Data:"),
    PERFORMANCE("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight performance and return it. This field should contain the performance level of the threshold breach (CRITICAL, UNSATISFACTORY, BELOW_EXPECTATIONS, EXCEEDS_EXPECTATIONS, OUTSTANDING, PIONEERING). KPI Data:"),
    ROOT_CAUSE("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight root cause and return it. This field should contain the identified root cause of the threshold breach. KPI Data:"),
    IMPACT("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight impact and return it. This field should contain the impact of the threshold breach on overall performance. KPI Data:"),
    PREVENTION("Analyze the following KPI data of a contact center and its threshold breach. Generate the insight prevention and return it. This field should contain recommendations for preventing future threshold breaches. KPI Data:");

    private final String prompt;
}