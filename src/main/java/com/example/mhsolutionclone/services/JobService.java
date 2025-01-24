package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Jobs;
import com.example.mhsolutionclone.data.mapper.JobMapper;
import com.example.mhsolutionclone.data.request.SearchFilter;
import com.example.mhsolutionclone.data.response.JobResponse;
import com.example.mhsolutionclone.data.response.PaginatedResponse;
import com.example.mhsolutionclone.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public List<JobResponse> getJobs() {
        List<Jobs> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(jobMapper::toJobResponse)
                .collect(Collectors.toList());
    }

    public PaginatedResponse<JobResponse> searchJobs(List<SearchFilter> filters, int page, int size) {
        List<Jobs> jobs;
        long totalElements;

        if (filters == null || filters.isEmpty()) {
            Map<List<Jobs>, Long> result = jobRepository.searchAll(page, size);
            jobs = result.keySet().iterator().next();
            totalElements = result.values().iterator().next();
        } else {
            Map<List<Jobs>, Long> result = jobRepository.search(filters, page, size);
            jobs = result.keySet().iterator().next();
            totalElements = result.values().iterator().next();
        }

        List<JobResponse> responses = jobs.stream()
                .map(jobMapper::toJobResponse)
                .collect(Collectors.toList());

        return new PaginatedResponse<>(totalElements, responses);
    }

    public JobResponse getJobBySeoId(String seoId) {
        Jobs job = jobRepository.findBySeoId(seoId);
        if (job == null) {
            throw new RuntimeException("Không tìm thấy công việc với seoId: " + seoId);
        }
        return jobMapper.toJobResponse(job);
    }
}

