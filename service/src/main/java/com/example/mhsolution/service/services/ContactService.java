package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.tables.pojos.Contact;
import com.example.mhsolution.domain.data.mappers.contact.ContactMapper;
import com.example.mhsolution.domain.data.responses.contact.ContactResponse;
import com.example.mhsolution.repository.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactResponse getContact() {
        Contact contact = contactRepository.findContact();
        if (contact == null) {
            throw new RuntimeException("Footer not found");
        }

        return contactMapper.toResponse(contact);
    }
}

