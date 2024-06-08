package com.springboot.connectmate.enums;

import com.amazonaws.services.connect.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ConnectMetricCode2 {
    SERVICE_LEVEL,
    ABANDONMENT_RATE,
    AVERAGE_SPEED_ANSWER,
    AVERAGE_HANDLE_TIME,
    OCCUPANCY,
    FIRST_CONTACT_RESOLUTION,
    AGENT_SCHEDULE_ADHERENCE,
    AVERAGE_AFTER_CONTACT_WORK_TIME,
    AVERAGE_QUEUE_ANSWER_TIME;

}
