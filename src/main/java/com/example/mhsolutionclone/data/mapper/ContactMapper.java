package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Contact;
import com.example.mhsolutionclone.data.response.ContactResponse;
import com.example.mhsolutionclone.data.response.ContactSocialResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSONB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    @Mapping(source = "seoId", target = "seo_id")
    @Mapping(source = "phoneNumber", target = "phone_number")
    @Mapping(source = "socials", target = "socials", qualifiedByName = "jsonToSocials")
    ContactResponse toContactResponse(Contact contact);

    @Named("jsonToSocials")
    default List<ContactSocialResponse> jsonToSocials(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<ContactSocialResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

