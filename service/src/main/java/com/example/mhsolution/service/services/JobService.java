package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.tables.pojos.Jobs;
import com.example.mhsolution.domain.data.mappers.job.JobMapper;
import com.example.mhsolution.domain.data.models.search.SearchRequest;
import com.example.mhsolution.domain.data.responses.job.JobResponse;
import com.example.mhsolution.domain.data.responses.PaginatedResponse;
import com.example.mhsolution.repository.repositories.JobRepository;
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
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PaginatedResponse<JobResponse> searchJobs(SearchRequest searchRequest) {
        Map<List<Jobs>, Long> result = jobRepository.search(searchRequest);
        List<Jobs> jobs = result.keySet().iterator().next();
        long totalElements = result.values().iterator().next();

        if (jobs.isEmpty()) {
            throw new RuntimeException("Không tìm thấy công việc nào!");
        }

        List<JobResponse> responses = jobs.stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());

        return new PaginatedResponse<>(totalElements, responses);
    }

    public JobResponse getJobById(Integer id) {
        Jobs job = jobRepository.findById(id);
        if (job == null) {
            throw new RuntimeException("Không tìm thấy công việc với ID: " + id);
        }
        return jobMapper.toResponse(job);
    }
}
