package com.example.eventmanagement.dto;

import jakarta.validation.constraints.NotNull;

public class RegistrationRequest {

    @NotNull
    private Integer eventId;

    @NotNull
    private Integer participantId;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }
}
