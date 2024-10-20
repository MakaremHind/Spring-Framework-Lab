package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.model.HeaterStatus;

public record Heater(
        Long id,
        String name,
        HeaterStatus status,
        Long roomId
) {
}