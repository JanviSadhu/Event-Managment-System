package com.example.eventmanagement.controller;

import com.example.eventmanagement.dto.RegistrationRequest;
import com.example.eventmanagement.model.Registration;
import com.example.eventmanagement.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Registration> getRegistrations() {
        return registrationService.getAll();
    }

    @PostMapping
    public Registration register(@Valid @RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
