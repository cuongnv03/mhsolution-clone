package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Categories;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.News;
import com.example.mhsolutionclone.data.response.CategoryResponse;
import com.example.mhsolutionclone.data.response.NewsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(source = "news.id", target = "id")
    @Mapping(source = "news.title", target = "title")
    @Mapping(source = "news.content", target = "content")
    @Mapping(source = "news.seoId", target = "seo_id")
    @Mapping(source = "news.categoryNewId", target = "category_new_id")
    @Mapping(source = "news.createdAt", target = "created_at")
    @Mapping(source = "news.coverUrl", target = "cover_url")
    @Mapping(source = "news.isHighlights", target = "is_highlights")
    NewsResponse toNewsResponse(News news, CategoryResponse category);

    @Mapping(source = "category.seoId", target = "seo_id")
    CategoryResponse toCategoryResponse(Categories category);
}

