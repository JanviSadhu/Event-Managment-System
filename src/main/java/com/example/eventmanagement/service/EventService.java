package com.example.eventmanagement.service;

import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.Participant;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public EventService(EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + id));
    }

    public Event getByName(String name) {
        return eventRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + name));
    }

    public void delete(Integer id) {
        Event event = getById(id);
        registrationRepository.deleteAll(registrationRepository.findByEventId(id));
        eventRepository.delete(event);
    }

    public List<Participant> getParticipantsForEvent(Integer eventId) {
        getById(eventId);
        return registrationRepository.findByEventId(eventId)
                .stream()
                .map(registration -> registration.getParticipant())
                .toList();
    }
}
