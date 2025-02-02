package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Categories;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.News;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Seo;
import com.example.mhsolutionclone.data.mapper.NewsMapper;
import com.example.mhsolutionclone.data.mapper.SeoMapper;
import com.example.mhsolutionclone.data.request.SearchFilter;
import com.example.mhsolutionclone.data.response.CategoryResponse;
import com.example.mhsolutionclone.data.response.NewsResponse;
import com.example.mhsolutionclone.data.response.PaginatedResponse;
import com.example.mhsolutionclone.data.response.SeoResponse;
import com.example.mhsolutionclone.repositories.NewsRepository;
import com.example.mhsolutionclone.repositories.SeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final SeoMapper seoMapper;
    private final SeoRepository seoRepository;

    public List<NewsResponse> getNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream()
                .map(news -> {
                    Categories category = newsRepository.findCategoryById(news.getCategoryNewId())
                            .orElse(null);
                    CategoryResponse categoryResponse = category == null ? null : newsMapper.toCategoryResponse(category);
                    Seo seo = seoRepository.findById(news.getSeoId())
                            .orElse(null);
                    SeoResponse seoResponse = seo == null ? null : seoMapper.toSeoResponse(seo);
                    return newsMapper.toNewsResponse(news, seoResponse, categoryResponse);
                })
                .collect(Collectors.toList());
    }

    public PaginatedResponse<NewsResponse> searchNews(List<SearchFilter> filters, int page, int size) {
        List<News> newsList;
        long totalElements;

        if (filters == null || filters.isEmpty()) {
            newsList = newsRepository.searchAll(page, size);
            totalElements = newsRepository.count();
        } else {
            newsList = newsRepository.searchByCategoryId(filters, page, size);
            totalElements = newsRepository.countSearch(filters);
        }

        List<NewsResponse> responses = newsList.stream()
                .map(news -> {
                    Categories category = newsRepository.findCategoryById(news.getCategoryNewId())
                            .orElse(null);
                    CategoryResponse categoryResponse = category == null ? null : newsMapper.toCategoryResponse(category);
                    Seo seo = seoRepository.findById(news.getSeoId())
                            .orElse(null);
                    SeoResponse seoResponse = seo == null ? null : seoMapper.toSeoResponse(seo);
                    return newsMapper.toNewsResponse(news, seoResponse, categoryResponse);
                })
                .collect(Collectors.toList());

        return new PaginatedResponse<>(totalElements, responses);
    }

    public NewsResponse getNewsBySeoId(String seoId) {
        News news = newsRepository.findBySeoId(seoId);
        Categories category = newsRepository.findCategoryById(news.getCategoryNewId())
                .orElse(null);
        CategoryResponse categoryResponse = category == null ? null : newsMapper.toCategoryResponse(category);
        Seo seo = seoRepository.findById(news.getSeoId())
                .orElse(null);
        SeoResponse seoResponse = seo == null ? null : seoMapper.toSeoResponse(seo);
        return newsMapper.toNewsResponse(news, seoResponse, categoryResponse);
    }
}

