package com.emse.spring.automacorp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_seq")
    @SequenceGenerator(name = "building_seq", sequenceName = "building_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)  // Name must be non-nullable
    private String name;

    @ManyToOne  // The outside temperature is measured by a sensor
    @JoinColumn(name = "outside_temperature_id", nullable = false)  // Non-nullable foreign key for the sensor
    private SensorEntity outsideTemperature;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)  // Bidirectional association with rooms
    private List<RoomEntity> rooms;

    // Default constructor
    public BuildingEntity() {}

    // Constructor with non-nullable fields
    public BuildingEntity(String name, SensorEntity outsideTemperature) {
        this.name = name;
        this.outsideTemperature = outsideTemperature;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorEntity getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(SensorEntity outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }
}
