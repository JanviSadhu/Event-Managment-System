package com.example.eventmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "event")
public class Event {

    @Id
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String location;

    @NotBlank
    @Column(nullable = false)
    private String date;

    @Min(1)
    @Column(name = "max_participants", nullable = false)
    private Integer maxParticipants;

    public Event() {
    }

    public Event(Integer id, String name, String location, String date, Integer maxParticipants) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.maxParticipants = maxParticipants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}
