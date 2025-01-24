package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.*;
import com.example.mhsolutionclone.data.response.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSONB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IntroductionMapper {
    @Mapping(source = "coverUrl", target = "cover_url")
    @Mapping(source = "visions", target = "visions", qualifiedByName = "jsonToVisions")
    @Mapping(source = "missions", target = "missions", qualifiedByName = "jsonToMissions")
    @Mapping(source = "coreValues", target = "coreValues", qualifiedByName = "jsonToCoreValues")
    @Mapping(source = "competitives", target = "competitives", qualifiedByName = "jsonToCompetitives")
    IntroductionResponse toIntroductionResponse(Introduction introduction);

    @Named("jsonToVisions")
    default List<VisionResponse> jsonToVisions(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<VisionResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Named("jsonToMissions")
    default List<MissionResponse> jsonToMissions(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<MissionResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Named("jsonToCoreValues")
    default List<CoreValueResponse> jsonToCoreValues(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<CoreValueResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Named("jsonToCompetitives")
    default List<CompetitiveResponse> jsonToCompetitives(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<CompetitiveResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

