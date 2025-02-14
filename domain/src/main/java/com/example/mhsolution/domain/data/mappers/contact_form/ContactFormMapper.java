package com.example.mhsolution.domain.data.mappers.contact_form;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.tables.pojos.ContactForms;
import com.example.mhsolution.domain.data.requests.contact_form.ContactFormRequest;
import com.example.mhsolution.domain.data.responses.contact_form.ContactFormResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactFormMapper extends BaseMapper<ContactFormRequest, ContactFormResponse, ContactForms> {
}

