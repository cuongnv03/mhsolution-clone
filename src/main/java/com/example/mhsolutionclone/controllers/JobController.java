package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.request.SearchFilter;
import com.example.mhsolutionclone.data.request.SearchRequest;
import com.example.mhsolutionclone.data.request.SearchSort;
import com.example.mhsolutionclone.data.response.JobResponse;
import com.example.mhsolutionclone.data.response.PaginatedResponse;
import com.example.mhsolutionclone.services.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
            @Valid @RequestBody(required = false) SearchRequest searchRequest) {
        PaginatedResponse<JobResponse> response;
        if (searchRequest == null) {
            searchRequest = SearchRequest.builder()
                    .page(1) // Default page
                    .limit(8) // Default limit
                    .sorts(SearchSort.builder()
                            .property("end_time")
                            .direction(SearchSort.SortDirection.DESC)
                            .build()) // Default sorting
                    .filters(Collections.emptyList()) // Default filters (empty list)
                    .build();
            response = jobService.searchJobs(searchRequest);
        } else {
            searchRequest = SearchRequest.builder()
                    .page((searchRequest.getPage() == 0) ? 1 : searchRequest.getPage())
                    .limit((searchRequest.getLimit() == 0) ? 8 : searchRequest.getLimit())
                    .sorts(SearchSort.builder()
                            .property((searchRequest.getSorts() != null && searchRequest.getSorts().getProperty() != null) ?
                                    searchRequest.getSorts().getProperty() : "end_time")
                            .direction((searchRequest.getSorts() != null && searchRequest.getSorts().getDirection() != null) ?
                                    searchRequest.getSorts().getDirection() : SearchSort.SortDirection.DESC)
                            .build())
                    .filters((searchRequest.getFilters() != null) ? searchRequest.getFilters() : Collections.emptyList())
                    .build();
            // Sử dụng searchRequest.filters thay vì filters riêng biệt
            response = jobService.searchJobs(searchRequest);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{seoId}")
    public ResponseEntity<JobResponse> getJobBySeoId(@PathVariable String seoId) {
        JobResponse response = jobService.getJobBySeoId(seoId);
        return ResponseEntity.ok(response);
    }
}