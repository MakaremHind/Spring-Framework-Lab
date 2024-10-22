package com.emse.spring.automacorp.dto;

public record RoomCommand(String name, Double currentTemperature, Double targetTemperature, int floor, long sensorid) {
}