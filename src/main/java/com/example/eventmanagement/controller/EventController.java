package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.model.Participant;
import com.example.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Integer id) {
        return eventService.getById(id);
    }

    @GetMapping("/search")
    public Event searchByName(@RequestParam String name) {
        return eventService.getByName(name);
    }

    @GetMapping("/{id}/participants")
    public List<Participant> getParticipantsForEvent(@PathVariable Integer id) {
        return eventService.getParticipantsForEvent(id);
    }

    @PostMapping
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventService.create(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.delete(id);
    }
}
