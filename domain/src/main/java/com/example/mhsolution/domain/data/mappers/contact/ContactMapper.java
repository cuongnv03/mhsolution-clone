package com.example.mhsolution.domain.data.mappers.contact;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.models.type.Social;
import com.example.mhsolution.domain.data.requests.contact.ContactRequest;
import com.example.mhsolution.domain.data.tables.pojos.Contact;
import com.example.mhsolution.domain.data.responses.contact.ContactResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jooq.JSONB;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public interface ContactMapper extends BaseMapper<ContactRequest, ContactResponse, Contact> {
    @Override
    @Named("toPOJO")
    @Mapping(target = "socials", ignore = true)
    Contact toPOJO(ContactRequest request);

    @Override
    @Named("toResponse")
    @Mapping(target = "socials", ignore = true)
    ContactResponse toResponse(Contact pojo);

    @Override
    @IterableMapping(qualifiedByName = "toResponse")
    List<ContactResponse> toResponses(List<Contact> pojos);

    @SneakyThrows
    @AfterMapping
    default void afterToPojo(@MappingTarget Contact contact, ContactRequest request) {
        if (request.getSocials() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String info = objectMapper.writeValueAsString(request.getSocials());
            contact.setSocials(JSONB.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    default void afterToResponse(@MappingTarget ContactResponse response, Contact contact) {
        if (contact.getSocials() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Social> socialResponses = objectMapper
                    .readValue(contact.getSocials().data(), new TypeReference<List<Social>>() {});
            response.setSocials(socialResponses);
        } else {
            response.setSocials(Collections.emptyList());
        }
    }

    @AfterMapping
    default void afterResponse(Contact contact, @MappingTarget ContactResponse response) {
        response.setSeoId(generateSeoId(response.getAddress(), String.valueOf(response.getId())));
    }
}

