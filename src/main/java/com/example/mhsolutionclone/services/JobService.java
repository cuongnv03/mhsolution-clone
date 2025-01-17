package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Jobs;
import com.example.mhsolutionclone.data.mapper.JobMapper;
import com.example.mhsolutionclone.data.request.SearchFilter;
import com.example.mhsolutionclone.data.response.JobInfoResponse;
import com.example.mhsolutionclone.data.response.JobResponse;
import com.example.mhsolutionclone.data.response.PaginatedResponse;
import com.example.mhsolutionclone.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public List<JobResponse> getJobs() {
        List<Jobs> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(job -> {
                    List<JobInfoResponse> infos = jobRepository.findInfosByJobId(job.getId())
                            .stream()
                            .map(jobMapper::toJobInfoResponse)
                            .collect(Collectors.toList());
                    return jobMapper.toJobResponse(job, infos);
                })
                .collect(Collectors.toList());
    }

    public PaginatedResponse<JobResponse> searchJobs(List<SearchFilter> filters, int page, int size) {
        List<Jobs> jobs;
        long totalElements;

        if (filters == null || filters.isEmpty()) {
            jobs = jobRepository.searchAll(page, size);
            totalElements = jobRepository.count();
        } else {
            jobs = jobRepository.search(filters, page, size);
            totalElements = jobRepository.countSearch(filters);
        }

        List<JobResponse> responses = jobs.stream()
                .map(job -> {
                    List<JobInfoResponse> infos = jobRepository.findInfosByJobId(job.getId())
                            .stream()
                            .map(jobMapper::toJobInfoResponse)
                            .collect(Collectors.toList());
                    return jobMapper.toJobResponse(job, infos);
                })
                .collect(Collectors.toList());

        return new PaginatedResponse<>(totalElements, responses);
    }

    public JobResponse getJobBySeoId(String seoId) {
        Jobs job = jobRepository.findBySeoId(seoId);
        if (job == null) {
            throw new RuntimeException("Không tìm thấy công việc với seoId: " + seoId);
        }
        List<JobInfoResponse> infos = jobRepository.findInfosByJobId(job.getId())
                .stream()
                .map(jobMapper::toJobInfoResponse)
                .collect(Collectors.toList());
        return jobMapper.toJobResponse(job, infos);
    }
}

