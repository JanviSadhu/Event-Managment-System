package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    long countByEventId(Integer eventId);
    boolean existsByEventIdAndParticipantId(Integer eventId, Integer participantId);
    List<Registration> findByEventId(Integer eventId);
    List<Registration> findByParticipantId(Integer participantId);
}
