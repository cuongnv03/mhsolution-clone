package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Products;
import com.example.mhsolutionclone.data.response.ProductContentResponse;
import com.example.mhsolutionclone.data.response.ProductInfoResponse;
import com.example.mhsolutionclone.data.response.ProductResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSONB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "seoId", target = "seo_id")
    @Mapping(source = "contents", target = "contents", qualifiedByName = "jsonToContents")
    @Mapping(source = "shortContent", target = "short_content")
    @Mapping(source = "infos", target = "infos", qualifiedByName = "jsonToInfos")
    @Mapping(source = "infoUrl", target = "info_url")
    @Mapping(source = "coverUrl", target = "cover_url")
    @Mapping(source = "iconUrl", target = "icon_url")
    @Mapping(source = "iconHoverUrl", target = "icon_hover_url")
    @Mapping(source = "phoneNumber", target = "phone_number")
    @Mapping(source = "createdAt", target = "created_at")
    ProductResponse toProductResponse(Products product);

    @Named("jsonToContents")
    default List<ProductContentResponse> jsonToContents(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<ProductContentResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Named("jsonToInfos")
    default List<ProductInfoResponse> jsonToInfos(JSONB json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json.data(), new TypeReference<List<ProductInfoResponse>>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
