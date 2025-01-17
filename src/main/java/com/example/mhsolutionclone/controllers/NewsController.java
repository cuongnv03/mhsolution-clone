package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.request.SearchFilter;
import com.example.mhsolutionclone.data.response.NewsResponse;
import com.example.mhsolutionclone.data.response.PaginatedResponse;
import com.example.mhsolutionclone.services.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<List<NewsResponse>> getNews() {
        List<NewsResponse> response = newsService.getNews();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/news")
    public ResponseEntity<PaginatedResponse<NewsResponse>> searchNews(
            @Valid @RequestBody(required = false) List<SearchFilter> filters,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        PaginatedResponse<NewsResponse> response = newsService.searchNews(filters, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/news/{seo_id}")
    public ResponseEntity<NewsResponse> getNewsBySeoId(@PathVariable String seo_id) {
        NewsResponse response = newsService.getNewsBySeoId(seo_id);
        return ResponseEntity.ok(response);
    }
}

