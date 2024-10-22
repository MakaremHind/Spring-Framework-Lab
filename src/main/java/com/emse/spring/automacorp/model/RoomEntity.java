package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne  // Current temperature is measured by a sensor
    @JoinColumn(name = "current_temperature_id", nullable = false)  // Non-nullable foreign key for the sensor
    private SensorEntity currentTemperature;

    @Column(name = "target_temperature")  // Target temperature column
    private Double targetTemperature;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)  // Bidirectional association with windows
    private Set<WindowEntity> windows;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)  // Bidirectional association with heaters
    private Set<HeaterEntity> heaters;

    @ManyToOne  // Many rooms belong to one building
    @JoinColumn(name = "building_id")  // Non-nullable building foreign key
    private BuildingEntity building;

    // Default constructor
    public RoomEntity() {}

    // Constructor with non-nullable fields
    public RoomEntity(String name, SensorEntity currentTemperature, Integer floor) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.floor = floor;
        this.windows = new HashSet<>();  // Ensure windows are initialized
        this.heaters = new HashSet<>();  // Ensure heaters are initialized
    }

    public RoomEntity(String name, Double aDouble, int floor) {
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
    public Set<WindowEntity> getWindows() {
        return windows;
    }

    public void setWindows(Set<WindowEntity> windows) {
        this.windows = windows;
    }

    public Set<HeaterEntity> getHeaters() {
        return heaters;
    }

    public void setHeaters(Set<HeaterEntity> heaters) {
        this.heaters = heaters;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
