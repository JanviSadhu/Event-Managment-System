package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.RegistrationRequest;
import com.example.eventmanagement.exception.EventFullException;
import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.Participant;
import com.example.eventmanagement.model.Registration;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.ParticipantRepository;
import com.example.eventmanagement.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            EventRepository eventRepository,
            ParticipantRepository participantRepository
    ) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }

    public Registration register(RegistrationRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + request.getEventId()));

        Participant participant = participantRepository.findById(request.getParticipantId())
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found: " + request.getParticipantId()));

        if (registrationRepository.existsByEventIdAndParticipantId(event.getId(), participant.getId())) {
            throw new IllegalStateException("Participant is already registered for this event");
        }

        long currentCount = registrationRepository.countByEventId(event.getId());
        if (currentCount >= event.getMaxParticipants()) {
            throw new EventFullException("Event is full");
        }

        return registrationRepository.save(new Registration(event, participant));
    }

    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }
}
