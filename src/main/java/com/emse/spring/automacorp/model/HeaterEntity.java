package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "heater")
public class HeaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "heater_seq")
    @SequenceGenerator(name = "heater_seq", sequenceName = "heater_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)  // Name must be non-nullable
    private String name;

    @ManyToOne  // Many heaters belong to one room
    @JoinColumn(name = "room_id", nullable = false)  // Non-nullable room foreign key
    private RoomEntity room;

    @ManyToOne  // The status is a sensor (ManyToOne since multiple heaters can be tracked by one sensor)
    @JoinColumn(name = "status_sensor_id", nullable = false)  // Non-nullable status foreign key
    private SensorEntity status;

    HeaterStatus heaterStatus = HeaterStatus.OFF;

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    // Default constructor
    public HeaterEntity() {}


    // Constructor with non-nullable fields
    public HeaterEntity(String name, SensorEntity status, RoomEntity room) {
        this.name = name;
        this.room = room;
        this.status = status;
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

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public SensorEntity getStatus() {
        return status;
    }

    public void setStatus(SensorEntity status) {
        this.status = status;
    }
}
