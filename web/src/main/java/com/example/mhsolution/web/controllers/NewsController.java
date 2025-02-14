package com.example.mhsolution.web.controllers;

import com.example.mhsolution.domain.data.models.ApiResponse;
import com.example.mhsolution.domain.data.models.search.SearchRequest;
import com.example.mhsolution.domain.data.responses.news.NewsResponse;
import com.example.mhsolution.domain.data.responses.PaginatedResponse;
import com.example.mhsolution.service.services.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsResponse>> getNews() {
        List<NewsResponse> response = newsService.getNews();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaginatedResponse<NewsResponse>>> searchNews(
            @Valid @RequestBody(required = false) SearchRequest searchRequest) {
        PaginatedResponse<NewsResponse> response = newsService.searchNews(searchRequest);
        ApiResponse<PaginatedResponse<NewsResponse>> apiResponse = new ApiResponse<>(0, "OK", response);
        return ResponseEntity.ok(apiResponse); // HTTP 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNewsById(@PathVariable Integer id) {
        NewsResponse response = newsService.getNewsById(id);
        return ResponseEntity.ok(response);
    }
}
