package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Participant;
import com.example.eventmanagement.service.ParticipantService;
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
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<Participant> getParticipants() {
        return participantService.getAll();
    }

    @GetMapping("/{id}")
    public Participant getParticipant(@PathVariable Integer id) {
        return participantService.getById(id);
    }

    @GetMapping("/search")
    public Participant searchByName(@RequestParam String name) {
        return participantService.getByName(name);
    }

    @PostMapping
    public Participant createParticipant(@Valid @RequestBody Participant participant) {
        return participantService.create(participant);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Integer id) {
        participantService.delete(id);
    }
}
