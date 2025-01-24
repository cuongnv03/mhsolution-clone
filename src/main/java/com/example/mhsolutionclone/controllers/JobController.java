package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.request.SearchFilter;
import com.example.mhsolutionclone.data.response.JobResponse;
import com.example.mhsolutionclone.data.response.PaginatedResponse;
import com.example.mhsolutionclone.services.JobService;
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
    public ResponseEntity<PaginatedResponse<JobResponse>> searchJobs(
            @Valid @RequestBody(required = false) List<SearchFilter> filters,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {
        PaginatedResponse<JobResponse> response = jobService.searchJobs(filters, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{seoId}")
    public ResponseEntity<JobResponse> getJobBySeoId(@PathVariable String seoId) {
        JobResponse response = jobService.getJobBySeoId(seoId);
        return ResponseEntity.ok(response);
    }
}
