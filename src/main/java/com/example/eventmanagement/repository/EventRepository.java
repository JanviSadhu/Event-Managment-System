package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findByNameIgnoreCase(String name);
}
