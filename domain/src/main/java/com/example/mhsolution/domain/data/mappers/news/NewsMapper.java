package com.example.mhsolution.domain.data.mappers.news;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.mappers.news_category.NewsCategoryMapper;
import com.example.mhsolution.domain.data.requests.news.NewsRequest;
import com.example.mhsolution.domain.data.responses.news_category.NewsCategoryResponse;
import com.example.mhsolution.domain.data.tables.pojos.Categories;
import com.example.mhsolution.domain.data.tables.pojos.News;
import com.example.mhsolution.domain.data.responses.news.NewsResponse;
import com.example.mhsolution.domain.data.models.type.Seo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring", uses = {NewsCategoryMapper.class})
public interface NewsMapper extends BaseMapper<NewsRequest, NewsResponse, News> {
    @Override
    @Named("toPOJO")
    @Mapping(source = "isHighlights", target = "isHighlights", qualifiedByName = "toBoolean")
    News toPOJO(NewsRequest request);

    @Override
    @Named("toResponse")
    @Mapping(source = "isHighlights", target = "isHighlights", qualifiedByName = "toInteger")
    NewsResponse toResponse(News news);

    @Mappings({
            @Mapping(source = "isHighlights", target = "isHighlights", qualifiedByName = "toInteger"),
            @Mapping(target = "category", ignore = true),
            @Mapping(target = "seo", ignore = true)
    })
    NewsResponse toResponse(News news, @Context NewsCategoryResponse newsCategoryResponse);

    default List<NewsResponse> toResponses(List<News> news, Map<Integer, NewsCategoryResponse> newsCategoryMap) {
        return news.stream()
                .map(n -> toResponse(n, newsCategoryMap.get(n.getCategoryNewId())))
                .collect(Collectors.toList());
    }

    @AfterMapping
    default void afterResponse(News pojo, @MappingTarget NewsResponse response, @Context NewsCategoryResponse categoryResponse) {
        response.setSeoId(generateSeoId(response.getTitle(), String.valueOf(response.getId())));
        response.setCategory(categoryResponse);
        Seo seo = new Seo()
                .setKeywords(pojo.getKeywords() != null ? String.join(", ", pojo.getKeywords()) : null)
                .setDescription(pojo.getDescription());
        response.setSeo(seo);
    }

    default void addCategory(NewsResponse response, Categories newsCategory) {
        NewsCategoryMapper newsCategoryMapper = Mappers.getMapper(NewsCategoryMapper.class);
        response.setCategory(newsCategoryMapper.toResponse(newsCategory));
    }

    @Named("toInteger")
    default Integer toInteger(Boolean value) {
        return value ? 1 : 0;
    }

    @Named("toBoolean")
    default Boolean toBoolean(Integer value) {
        return value == 1;
    }

    @Named("arrayToString")
    default String arrayToString(String[] value) {
        if (value == null) {
            return ""; // Return an empty string if the array is null
        }
        return String.join(", ", value);
    }
}
