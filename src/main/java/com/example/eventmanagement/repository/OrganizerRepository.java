package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}
