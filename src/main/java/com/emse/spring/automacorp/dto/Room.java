package com.emse.spring.automacorp.dto;

import java.util.List;

public record Room(Long id, String name, int floor, Double currentTemperature, Double targetTemperature, List<Window>windows, List<Heater>heaters) {

}