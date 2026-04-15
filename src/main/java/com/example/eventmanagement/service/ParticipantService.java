package com.example.eventmanagement.service;

import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.model.Participant;
import com.example.eventmanagement.repository.ParticipantRepository;
import com.example.eventmanagement.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public ParticipantService(ParticipantRepository participantRepository, RegistrationRepository registrationRepository) {
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }

    public Participant create(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAll() {
        return participantRepository.findAll();
    }

    public Participant getById(Integer id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found: " + id));
    }

    public Participant getByName(String name) {
        return participantRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found: " + name));
    }

    public void delete(Integer id) {
        Participant participant = getById(id);
        registrationRepository.deleteAll(registrationRepository.findByParticipantId(id));
        participantRepository.delete(participant);
    }
}
