package com.example.mhsolution.web.controllers;

import com.example.mhsolution.domain.data.requests.contact_form.ContactFormRequest;
import com.example.mhsolution.domain.data.responses.contact_form.ContactFormResponse;
import com.example.mhsolution.service.services.ContactFormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-form")
@RequiredArgsConstructor
public class ContactFormController {

    private final ContactFormService contactFormService;

    @PostMapping
    public ResponseEntity<ContactFormResponse> submitContactForm(@Valid @RequestBody ContactFormRequest request) {
        ContactFormResponse response = contactFormService.submitContactForm(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ContactFormResponse>> getAllContactForms() {
        List<ContactFormResponse> responses = contactFormService.getAllContactForms();
        return ResponseEntity.ok(responses);
    }
}

