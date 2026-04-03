package model;

import java.time.LocalDate;

public class Registration {

    private int registrationId;
    private Event event;
    private Participant participant;
    private LocalDate date;

    public Registration(int registrationId, Event event, Participant participant) {
        this.registrationId = registrationId;
        this.event = event;
        this.participant = participant;
        this.date = LocalDate.now();
    }

    // Getters & Setters
    public int getRegistrationId() { return registrationId; }
    public Event getEvent() { return event; }
    public Participant getParticipant() { return participant; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + registrationId +
                ", event=" + event.getName() +
                ", participant=" + participant.getName() +
                ", date=" + date +
                '}';
    }
}