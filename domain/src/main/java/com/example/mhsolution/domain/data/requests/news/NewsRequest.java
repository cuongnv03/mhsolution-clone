package com.example.mhsolution.domain.data.requests.news;

import com.example.mhsolution.domain.data.models.type.Seo;
import com.example.mhsolution.domain.data.responses.news_category.NewsCategoryResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsRequest {
    private String title;
    private String content;
    private Seo seo;
    private Integer categoryNewId;
    private LocalDate createdAt;
    private NewsCategoryResponse category;
    private String coverUrl;
    private Integer isHighlights;
}
