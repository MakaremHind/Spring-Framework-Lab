package com.emse.spring.automacorp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sp_window")  // Defines the table name as "SP_WINDOW"
public class WindowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "window_seq")
    @SequenceGenerator(name = "window_seq", sequenceName = "sp_window_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)  // Name must be non-nullable
    private String name;

    @ManyToOne  // The window status is related to the sensor entity
    @JoinColumn(name = "senosr_status_id", nullable = false)
    private SensorEntity sensorStatus;



    @ManyToOne  // Many windows belong to one room (bidirectional association)
    @JoinColumn(name = "room_id", nullable = false)  // The foreign key column to the room
    private RoomEntity room;

    WindowStatus windowStatus = WindowStatus.CLOSED;

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    // Default constructor
    public WindowEntity() {
    }

    // Constructor with room and window sensor status
    public WindowEntity(String name, SensorEntity windowStatus, RoomEntity room) {
        this.name = name;
        this.sensorStatus = windowStatus;
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

    public SensorEntity getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(SensorEntity windowStatus) {
        this.sensorStatus = windowStatus;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "WindowEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sensorStatus=" + sensorStatus +
                ", room=" + room +
                ", windowStatus=" + windowStatus +
                '}';
    }
}
