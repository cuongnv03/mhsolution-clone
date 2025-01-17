package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.request.ContactFormRequest;
import com.example.mhsolutionclone.data.response.ContactFormResponse;
import com.example.mhsolutionclone.services.ContactFormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ContactFormController {

    private final ContactFormService contactFormService;

    @PostMapping("/contact-forms")
    public ResponseEntity<ContactFormResponse> submitContactForm(@Valid @RequestBody ContactFormRequest request) {
        ContactFormResponse response = contactFormService.submitContactForm(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/contact-forms")
    public ResponseEntity<List<ContactFormResponse>> getAllContactForms() {
        List<ContactFormResponse> responses = contactFormService.getAllContactForms();
        return ResponseEntity.ok(responses);
    }
}

