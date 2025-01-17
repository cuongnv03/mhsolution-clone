package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.ContactForms;
import com.example.mhsolutionclone.data.request.ContactFormRequest;
import com.example.mhsolutionclone.data.response.ContactFormResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactFormMapper {
    @Mapping(source = "full_name", target = "fullName")
    @Mapping(source = "phone_number", target = "phoneNumber")
    @Mapping(target = "submittedAt", ignore = true)
    ContactForms toContactForm(ContactFormRequest request);

    @Mapping(source = "fullName", target = "full_name")
    @Mapping(source = "phoneNumber", target = "phone_number")
    @Mapping(source = "submittedAt", target = "submitted_at")
    ContactFormResponse toContactFormResponse(ContactForms contactForm);
}

