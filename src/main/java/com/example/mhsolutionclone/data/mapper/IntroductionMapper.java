package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.*;
import com.example.mhsolutionclone.data.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IntroductionMapper {
    @Mapping(source = "introduction.title", target = "title")
    @Mapping(source = "introduction.content", target = "content")
    @Mapping(source = "introduction.coverUrl", target = "cover_url")
    @Mapping(source = "visions", target = "visions")
    @Mapping(source = "missions", target = "missions")
    @Mapping(source = "coreValues", target = "coreValues")
    @Mapping(source = "competitives", target = "competitives")
    DataIntroductionResponse fromPojoToResponse(
            DataIntroduction introduction,
            List<VisionResponse> visions,
            List<MissionResponse> missions,
            List<CoreValueResponse> coreValues,
            List<CompetitiveResponse> competitives
    );

    VisionResponse toVisionResponse(Visions vision);

    MissionResponse toMissionResponse(Missions mission);

    CoreValueResponse toCoreValueResponse(CoreValues coreValue);

    CompetitiveResponse toCompetitiveResponse(Competitives competitive);
}

