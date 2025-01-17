package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.ContactForms;
import com.example.mhsolutionclone.data.mapper.ContactFormMapper;
import com.example.mhsolutionclone.data.request.ContactFormRequest;
import com.example.mhsolutionclone.data.response.ContactFormResponse;
import com.example.mhsolutionclone.repositories.ContactFormRepository;
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
        ContactForms contactForm = contactFormMapper.toContactForm(request);
        ContactForms savedContactForm = contactFormRepository.save(contactForm);
        // Fetch the saved entity to include database-generated fields like submitted_at
        ContactForms reloadedContactForm = contactFormRepository.findById(savedContactForm.getId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch saved contact form."));
        return contactFormMapper.toContactFormResponse(reloadedContactForm);
    }

    public List<ContactFormResponse> getAllContactForms() {
        List<ContactForms> contactForms = contactFormRepository.findAll();
        return contactForms.stream()
                .map(contactFormMapper::toContactFormResponse)
                .collect(Collectors.toList());
    }
}

