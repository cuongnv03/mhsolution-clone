package com.example.mhsolution.domain.data.mappers.product;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.models.type.Info;
import com.example.mhsolution.domain.data.requests.product.ProductRequest;
import com.example.mhsolution.domain.data.tables.pojos.Products;
import com.example.mhsolution.domain.data.responses.product.ProductResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jooq.JSONB;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductRequest, ProductResponse, Products> {
    @Override
    @Named("toPOJO")
    @Mappings({
            @Mapping(target = "contents", ignore = true),
            @Mapping(target = "infos", ignore = true)
    })
    Products toPOJO(ProductRequest request);

    @Override
    @IterableMapping(qualifiedByName = "toPOJO")
    List<Products> toPOJOs(List<ProductRequest> requests);

    @Override
    @Named("toResponse")
    @Mappings({
            @Mapping(target = "contents", ignore = true),
            @Mapping(target = "infos", ignore = true)
    })
    ProductResponse toResponse(Products pojo);

    @Override
    @IterableMapping(qualifiedByName = "toResponse")
    List<ProductResponse> toResponses(List<Products> pojos);

    @SneakyThrows
    @AfterMapping
    default void afterToPojo(@MappingTarget Products product, ProductRequest request) {
        if (request.getContents() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String contents = objectMapper.writeValueAsString(request.getContents());
            product.setContents(JSONB.valueOf(contents));
        }

        if (request.getInfos() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final String infos = objectMapper.writeValueAsString(request.getInfos());
            product.setInfos(JSONB.valueOf(infos));
        }
    }

    @SneakyThrows
    @AfterMapping
    default void afterToResponse(@MappingTarget ProductResponse response, Products product) {
        if (product.getContents() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Info> contentResponses = objectMapper
                    .readValue(product.getContents().data(), new TypeReference<List<Info>>() {});
            response.setContents(contentResponses);
        } else {
            response.setContents(Collections.emptyList());
        }

        if (product.getInfos() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            final List<Info> infoResponses = objectMapper
                    .readValue(product.getInfos().data(), new TypeReference<List<Info>>() {});
            response.setInfos(infoResponses);
        } else {
            response.setInfos(Collections.emptyList());
        }
    }

    @AfterMapping
    default void afterResponse(Products product, @MappingTarget ProductResponse response) {
        response.setSeoId(generateSeoId(response.getTitle(), String.valueOf(response.getId())));
    }
}
