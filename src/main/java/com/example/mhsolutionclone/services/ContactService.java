package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Contact;
import com.example.mhsolutionclone.data.mapper.ContactMapper;
import com.example.mhsolutionclone.data.response.ContactResponse;
import com.example.mhsolutionclone.data.response.ContactSocialResponse;
import com.example.mhsolutionclone.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        return contactMapper.toContactResponse(contact);
    }
}

