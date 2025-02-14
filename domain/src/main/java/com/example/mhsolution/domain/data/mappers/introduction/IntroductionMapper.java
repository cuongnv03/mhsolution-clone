package com.example.mhsolution.domain.data.mappers.introduction;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.models.type.Competitive;
import com.example.mhsolution.domain.data.models.type.CoreValue;
import com.example.mhsolution.domain.data.models.type.Mission;
import com.example.mhsolution.domain.data.models.type.Vision;
import com.example.mhsolution.domain.data.requests.introduction.IntroductionRequest;
import com.example.mhsolution.domain.data.responses.introduction.*;
import com.example.mhsolution.domain.data.tables.pojos.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jooq.JSONB;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IntroductionMapper extends BaseMapper<IntroductionRequest, IntroductionResponse, Introduction> {
    @Override
    @Named("toPOJO")
    @Mappings({
            @Mapping(target = "visions", ignore = true),
            @Mapping(target = "missions", ignore = true),
            @Mapping(target = "coreValues", ignore = true),
            @Mapping(target = "competitives", ignore = true),
    })
    Introduction toPOJO(IntroductionRequest request);

    @Override
    @IterableMapping(qualifiedByName = "toPOJO")
    List<Introduction> toPOJOs(List<IntroductionRequest> requests);

    @Override
    @Named("toResponse")
    @Mappings({
            @Mapping(target = "visions", ignore = true),
            @Mapping(target = "missions", ignore = true),
            @Mapping(target = "coreValues", ignore = true),
            @Mapping(target = "competitives", ignore = true),
    })
    IntroductionResponse toResponse(Introduction pojo);

    @Override
    @IterableMapping(qualifiedByName = "toResponse")
    List<IntroductionResponse> toResponses(List<Introduction> pojos);

    @SneakyThrows
    @AfterMapping
    default void afterToPojo(@MappingTarget Introduction introduction, IntroductionRequest request) {
        if (request.getVisions() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String vision = objectMapper.writeValueAsString(request.getVisions());
            introduction.setVisions(JSONB.valueOf(vision));
        }

        if (request.getMissions() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String mission = objectMapper.writeValueAsString(request.getMissions());
            introduction.setMissions(JSONB.valueOf(mission));
        }

        if (request.getCoreValues() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String coreValue = objectMapper.writeValueAsString(request.getCoreValues());
            introduction.setCoreValues(JSONB.valueOf(coreValue));
        }

        if (request.getCompetitives() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String competitive = objectMapper.writeValueAsString(request.getCompetitives());
            introduction.setCompetitives(JSONB.valueOf(competitive));
        }
    }

    @SneakyThrows
    @AfterMapping
    default void afterToResponse(@MappingTarget IntroductionResponse response, Introduction introduction) {
        if (introduction.getVisions() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Vision> visionResponses = objectMapper
                    .readValue(introduction.getVisions().data(), new TypeReference<List<Vision>>() {});
            response.setVisions(visionResponses);
        } else {
            response.setVisions(Collections.emptyList());
        }

        if (introduction.getMissions() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Mission> missionResponses = objectMapper
                    .readValue(introduction.getMissions().data(), new TypeReference<List<Mission>>() {});
            response.setMissions(missionResponses);
        } else {
            response.setMissions(Collections.emptyList());
        }

        if (introduction.getCoreValues() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<CoreValue> coreValueResponses = objectMapper
                    .readValue(introduction.getCoreValues().data(), new TypeReference<List<CoreValue>>() {});
            response.setCoreValues(coreValueResponses);
        } else {
            response.setCoreValues(Collections.emptyList());
        }

        if (introduction.getCompetitives() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Competitive> competitiveResponses = objectMapper
                    .readValue(introduction.getCompetitives().data(), new TypeReference<List<Competitive>>() {});
            response.setCompetitives(competitiveResponses);
        } else {
            response.setCompetitives(Collections.emptyList());
        }
    }
}
