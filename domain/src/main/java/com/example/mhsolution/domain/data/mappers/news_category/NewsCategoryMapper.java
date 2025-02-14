package com.example.mhsolution.domain.data.mappers.news_category;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.requests.news_category.NewsCategoryRequest;
import com.example.mhsolution.domain.data.responses.news_category.NewsCategoryResponse;
import com.example.mhsolution.domain.data.tables.pojos.Categories;
import org.mapstruct.*;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public interface NewsCategoryMapper extends BaseMapper<NewsCategoryRequest, NewsCategoryResponse, Categories> {
    @AfterMapping
    default void afterResponse(@MappingTarget NewsCategoryResponse response) {
        response.setSeoId(generateSeoId(response.getTitle(), String.valueOf(response.getId())));
    }
}
