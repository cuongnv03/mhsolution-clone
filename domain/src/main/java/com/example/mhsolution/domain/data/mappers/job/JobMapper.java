package com.example.mhsolution.domain.data.mappers.job;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.requests.job.JobRequest;
import com.example.mhsolution.domain.data.tables.pojos.Jobs;
import com.example.mhsolution.domain.data.models.type.Info;
import com.example.mhsolution.domain.data.responses.job.JobResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jooq.JSONB;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public interface JobMapper extends BaseMapper<JobRequest, JobResponse, Jobs> {
    @Override
    @Named("toPOJO")
    @Mapping(target = "info", ignore = true)
    Jobs toPOJO(JobRequest request);

    @Override
    @Named("toResponse")
    @Mapping(target = "info", ignore = true)
    JobResponse toResponse(Jobs job);

    @SneakyThrows
    @AfterMapping
    default void afterToPojo(@MappingTarget Jobs job, JobRequest request) {
        if (request.getInfo() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String info = objectMapper.writeValueAsString(request.getInfo());
            job.setInfo(JSONB.valueOf(info));
        }
    }

    @SneakyThrows
    @AfterMapping
    default void afterToResponse(@MappingTarget JobResponse response, Jobs job) {
        if (job.getInfo() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Info> infoResponses = objectMapper
                    .readValue(job.getInfo().data(), new TypeReference<List<Info>>() {});
            response.setInfo(infoResponses);
        } else {
            response.setInfo(Collections.emptyList());
        }
    }

    @AfterMapping
    default void afterResponse(Jobs job, @MappingTarget JobResponse response) {
        response.setSeoId(generateSeoId(response.getTitle(), String.valueOf(response.getId())));
    }
}

