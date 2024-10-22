package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.WindowStatus;

public record WindowCommand(String name, long sensorid, Long roomId) {
}