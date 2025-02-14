package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.mappers.news_category.NewsCategoryMapper;
import com.example.mhsolution.domain.data.models.search.SearchRequest;
import com.example.mhsolution.domain.data.responses.news_category.NewsCategoryResponse;
import com.example.mhsolution.domain.data.tables.pojos.Categories;
import com.example.mhsolution.domain.data.tables.pojos.News;
import com.example.mhsolution.domain.data.mappers.news.NewsMapper;
import com.example.mhsolution.domain.data.responses.news.NewsResponse;
import com.example.mhsolution.domain.data.responses.PaginatedResponse;
import com.example.mhsolution.repository.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final NewsCategoryMapper newsCategoryMapper;

    public List<NewsResponse> getNews() {
        List<News> newsList = newsRepository.findAll();
        return mapNewsToResponses(newsList);
    }

    public PaginatedResponse<NewsResponse> searchNews(SearchRequest searchRequest) {
        Map<List<News>, Long> result = newsRepository.search(searchRequest);
        List<News> newsList = result.keySet().iterator().next();
        long totalElements = result.values().iterator().next();

        if (newsList.isEmpty()) {
            throw new RuntimeException("Không tìm thấy tin tức nào!");
        }

        List<NewsResponse> responses = mapNewsToResponses(newsList);

        return new PaginatedResponse<>(totalElements, responses);
    }

    private List<NewsResponse> mapNewsToResponses(List<News> newsList) {
        // Collect all unique category IDs from news list
        Set<Integer> categoryIds = newsList.stream()
                .map(News::getCategoryNewId)
                .collect(Collectors.toSet());

        // Fetch all categories in one query
        List<Categories> categories = newsRepository.findCategoriesByIds(categoryIds);

        Map<Integer, NewsCategoryResponse> categoryMap = categories.stream()
                .map(newsCategoryMapper::toResponse)
                .collect(Collectors.toMap(
                        NewsCategoryResponse::getId,
                        Function.identity()
                ));

        // Use NewsMapper's batch mapping method
        return newsMapper.toResponses(newsList, categoryMap);
    }

    public NewsResponse getNewsById(Integer id) {
        News news = newsRepository.findById(id);
        Categories category = newsRepository.findCategoryById(news.getCategoryNewId())
                .orElse(null);
        NewsCategoryResponse categoryResponse = category == null ? null : newsCategoryMapper.toResponse(category);
        return newsMapper.toResponse(news, categoryResponse);
    }
}

