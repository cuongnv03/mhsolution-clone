package com.example.mhsolution.web.controllers;

import com.example.mhsolution.domain.data.models.search.SearchRequest;
import com.example.mhsolution.domain.data.models.ApiResponse;
import com.example.mhsolution.domain.data.responses.job.JobResponse;
import com.example.mhsolution.domain.data.responses.PaginatedResponse;
import com.example.mhsolution.service.services.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobResponse>> getJobs() {
        List<JobResponse> response = jobService.getJobs();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaginatedResponse<JobResponse>>> searchJobs(
            @Valid @RequestBody(required = false) SearchRequest searchRequest) {
            PaginatedResponse<JobResponse> response = jobService.searchJobs(searchRequest);
            ApiResponse<PaginatedResponse<JobResponse>> apiResponse = new ApiResponse<>(0, "OK", response);
            return ResponseEntity.ok(apiResponse); // HTTP 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Integer id) {
        JobResponse response = jobService.getJobById(id);
        return ResponseEntity.ok(response);
    }
}