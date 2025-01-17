package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.JobInfos;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Jobs;
import com.example.mhsolutionclone.data.response.JobInfoResponse;
import com.example.mhsolutionclone.data.response.JobResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    JobResponse toJobResponse(Jobs job, List<JobInfoResponse> infos);

    @Mapping(source = "jobInfo.title", target = "title")
    @Mapping(source = "jobInfo.content", target = "content")
    JobInfoResponse toJobInfoResponse(JobInfos jobInfo);
}

