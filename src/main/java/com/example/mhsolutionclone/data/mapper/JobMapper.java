package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Jobs;
import com.example.mhsolutionclone.data.response.JobInfoResponse;
import com.example.mhsolutionclone.data.response.JobResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSONB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {

    @Mapping(source = "job.seoId", target = "seo_id")
    @Mapping(source = "job.jobDescription", target = "job_description")
    @Mapping(source = "job.jobRequirements", target = "job_requirements")
    @Mapping(source = "job.jobBenefits", target = "job_benefits")
    @Mapping(source = "job.workExperience", target = "work_experience")
    @Mapping(source = "job.workType", target = "work_type")
    @Mapping(source = "job.workAddress", target = "work_address")
    @Mapping(source = "job.numberRecruits", target = "number_recruits")
    @Mapping(source = "job.endTime", target = "end_time")
    @Mapping(source = "job.coverUrl", target = "cover_url")
    @Mapping(source = "info", target = "info", qualifiedByName = "jsonToJobInfo")
    JobResponse toJobResponse(Jobs job);

    @Named("jsonToJobInfo")
    default List<JobInfoResponse> jsonToJobInfo(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<JobInfoResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

