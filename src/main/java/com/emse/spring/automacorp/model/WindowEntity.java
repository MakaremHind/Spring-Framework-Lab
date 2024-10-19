package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SP_WINDOW")  // Defines the table name as "SP_WINDOW"
public class WindowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "window_seq")
    @SequenceGenerator(name = "window_seq", sequenceName = "sp_window_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)  // Name must be non-nullable
    private String name;

    @ManyToOne  // The window status is related to the sensor entity
    @JoinColumn(name = "window_status_id", nullable = false)
    private SensorEntity windowStatus;

    @ManyToOne  // Many windows belong to one room (bidirectional association)
    @JoinColumn(name = "room_id", nullable = false)  // The foreign key column to the room
    private RoomEntity room;

    // Default constructor
    public WindowEntity() {
    }

    // Constructor with room and window sensor status
    public WindowEntity(String name, SensorEntity windowStatus, RoomEntity room) {
        this.name = name;
        this.windowStatus = windowStatus;
        this.room = room;
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

    public SensorEntity getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(SensorEntity windowStatus) {
        this.windowStatus = windowStatus;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
