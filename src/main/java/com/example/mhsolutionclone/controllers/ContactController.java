package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.ContactResponse;
import com.example.mhsolutionclone.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/our-contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<ContactResponse> getContact() {
        ContactResponse contact = contactService.getContact();
        return ResponseEntity.ok(contact);
    }
}

