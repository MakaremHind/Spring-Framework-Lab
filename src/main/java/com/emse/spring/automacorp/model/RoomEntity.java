package com.emse.spring.automacorp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sp_room")  // Defines the table name as "ROOM"
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    @SequenceGenerator(name = "room_seq", sequenceName = "sp_room_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)  // Floor must be non-nullable
    private Integer floor;

    @Column(nullable = false)  // Name must be non-nullable
    private String name;

    @ManyToOne  // Current temperature is measured by a sensor, so a ManyToOne association
    @JoinColumn(name = "current_temperature_id")  // Foreign key column for sensor
    private SensorEntity currentTemperature;

    @Column(name = "target_temperature")  // Target temperature column
    private Double targetTemperature;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)  // Bidirectional association with windows
    private List<WindowEntity> windows;

    // Default constructor
    public RoomEntity() {
    }

    // Constructor with non-nullable fields
    public RoomEntity(Integer floor, String name, Double targetTemperature) {
        this.floor = floor;
        this.name = name;
        this.targetTemperature = targetTemperature;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorEntity getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public List<WindowEntity> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowEntity> windows) {
        this.windows = windows;
    }
}
