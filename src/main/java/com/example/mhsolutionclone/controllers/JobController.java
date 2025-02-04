package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.request.SearchRequest;
import com.example.mhsolutionclone.data.response.ApiResponse;
import com.example.mhsolutionclone.data.response.JobResponse;
import com.example.mhsolutionclone.data.response.PaginatedResponse;
import com.example.mhsolutionclone.services.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{seoId}")
    public ResponseEntity<JobResponse> getJobBySeoId(@PathVariable String seoId) {
        JobResponse response = jobService.getJobBySeoId(seoId);
        return ResponseEntity.ok(response);
    }
}