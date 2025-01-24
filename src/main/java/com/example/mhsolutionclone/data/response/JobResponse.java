package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private Integer id;
    private String seo_id;
    private String title;
    private String salary;
    private String level;
    private String career;
    private String job_description;
    private String job_requirements;
    private String job_benefits;
    private String work_experience;
    private String work_type;
    private String work_address;
    private Integer number_recruits;
    private LocalDateTime end_time;
    private String cover_url;
    private List<JobInfoResponse> info;
}
