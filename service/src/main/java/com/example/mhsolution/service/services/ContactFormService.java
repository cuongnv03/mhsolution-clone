package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.tables.pojos.ContactForms;
import com.example.mhsolution.domain.data.mappers.contact_form.ContactFormMapper;
import com.example.mhsolution.domain.data.requests.contact_form.ContactFormRequest;
import com.example.mhsolution.domain.data.responses.contact_form.ContactFormResponse;
import com.example.mhsolution.repository.repositories.ContactFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;
    private final ContactFormMapper contactFormMapper;

    public ContactFormResponse submitContactForm(ContactFormRequest request) {
        ContactForms contactForm = contactFormMapper.toPOJO(request);
        ContactForms savedContactForm = contactFormRepository.save(contactForm);
        // Fetch the saved entity to include database-generated fields like submitted_at
        ContactForms reloadedContactForm = contactFormRepository.findById(savedContactForm.getId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch saved contact form."));
        return contactFormMapper.toResponse(reloadedContactForm);
    }

    public List<ContactFormResponse> getAllContactForms() {
        List<ContactForms> contactForms = contactFormRepository.findAll();
        return contactForms.stream()
                .map(contactFormMapper::toResponse)
                .collect(Collectors.toList());
    }
}

