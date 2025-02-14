package com.example.mhsolution.domain.data.responses.job;

import com.example.mhsolution.domain.data.models.type.Info;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobResponse {
    private Integer id;
    private String seoId;
    private String title;
    private String salary;
    private String level;
    private String career;
    private String jobDescription;
    private String jobRequirements;
    private String jobBenefits;
    private String workExperience;
    private String workType;
    private String workAddress;
    private Integer numberRecruits;
    private LocalDateTime endTime;
    private String coverUrl;
    private List<Info> info;
}
