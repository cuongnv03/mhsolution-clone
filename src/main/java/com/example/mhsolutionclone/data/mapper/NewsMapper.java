package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Categories;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.News;
import com.example.mhsolutionclone.data.response.CategoryResponse;
import com.example.mhsolutionclone.data.response.NewsResponse;
import com.example.mhsolutionclone.data.response.SeoResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(source = "news.id", target = "id")
    @Mapping(source = "news.title", target = "title")
    @Mapping(source = "news.content", target = "content")
    @Mapping(source = "news.seoId", target = "seo_id")
    @Mapping(target = "seo", ignore = true)
    @Mapping(source = "news.categoryNewId", target = "category_new_id")
    @Mapping(target = "category", ignore = true)
    @Mapping(source = "news.createdAt", target = "created_at")
    @Mapping(source = "news.coverUrl", target = "cover_url")
    @Mapping(source = "news.isHighlights", target = "is_highlights", qualifiedByName = "toInteger")
    NewsResponse toNewsResponse(News news);

    @AfterMapping
    private void setSeo(@MappingTarget NewsResponse newsResponse,
                        @Context SeoResponse seoResponse,
                        @Context CategoryResponse categoryResponse) {
        newsResponse.setSeo(seoResponse);
        newsResponse.setCategory(categoryResponse);
    }

    default NewsResponse toNewsResponse(News news, SeoResponse seoResponse, CategoryResponse categoryResponse) {
        NewsResponse newsResponse = toNewsResponse(news);
        setSeo(newsResponse, seoResponse, categoryResponse);
        return newsResponse;
    }

    @Mapping(source = "category.seoId", target = "seo_id")
    CategoryResponse toCategoryResponse(Categories category);

    @Named("toInteger")
    default Integer toInteger(Boolean value) {
        return value ? 1 : 0;
    }
}

