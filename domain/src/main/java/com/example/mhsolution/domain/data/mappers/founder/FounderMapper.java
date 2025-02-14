package com.example.mhsolution.domain.data.mappers.founder;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.requests.founder.FounderRequest;
import com.example.mhsolution.domain.data.tables.pojos.Founders;
import com.example.mhsolution.domain.data.responses.founder.FounderResponse;
import com.example.mhsolution.domain.data.models.type.Social;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jooq.JSONB;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public interface FounderMapper extends BaseMapper<FounderRequest, FounderResponse, Founders> {
    @Override
    @Named("toPOJO")
    @Mapping(target = "socials", ignore = true)
    Founders toPOJO(FounderRequest request);

    @Override
    @Named("toResponse")
    @Mapping(target = "socials", ignore = true)
    FounderResponse toResponse(Founders founder);

    @Override
    @IterableMapping(qualifiedByName = "toResponse")
    List<FounderResponse> toResponses(List<Founders> pojos);

    @SneakyThrows
    @AfterMapping
    default void afterToPojo(@MappingTarget Founders founder, FounderRequest request) {
        if (request.getSocials() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String info = objectMapper.writeValueAsString(request.getSocials());
            founder.setSocials(JSONB.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    default void afterToResponse(@MappingTarget FounderResponse response, Founders founder) {
        if (founder.getSocials() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Social> socialResponses = objectMapper
                    .readValue(founder.getSocials().data(), new TypeReference<List<Social>>() {});
            response.setSocials(socialResponses);
        } else {
            response.setSocials(Collections.emptyList());
        }
    }

    @AfterMapping
    default void afterResponse(Founders founder, @MappingTarget FounderResponse response) {
        response.setSeoId(generateSeoId(response.getName(), String.valueOf(response.getId())));
    }
}
