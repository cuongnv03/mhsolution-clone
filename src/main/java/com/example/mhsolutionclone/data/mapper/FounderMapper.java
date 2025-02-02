package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Founders;
import com.example.mhsolutionclone.data.response.ContactSocialResponse;
import com.example.mhsolutionclone.data.response.FounderResponse;
import com.example.mhsolutionclone.data.response.SocialResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSONB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FounderMapper {
    @Mapping(source = "seoId", target = "seo_id")
    @Mapping(source = "jobTitle", target = "job_title")
    @Mapping(source = "socials", target = "socials", qualifiedByName = "jsonToSocials")
    FounderResponse toFounderResponse(Founders founder);

    @Named("jsonToSocials")
    default List<SocialResponse> jsonToSocials(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<SocialResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

