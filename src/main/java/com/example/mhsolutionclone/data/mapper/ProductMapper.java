package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.ProductContents;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.ProductInfos;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Products;
import com.example.mhsolutionclone.data.response.ProductContentResponse;
import com.example.mhsolutionclone.data.response.ProductInfoResponse;
import com.example.mhsolutionclone.data.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "product.seoId", target = "seo_id")
    @Mapping(source = "product.shortContent", target = "short_content")
    @Mapping(source = "product.infoUrl", target = "info_url")
    @Mapping(source = "product.coverUrl", target = "cover_url")
    @Mapping(source = "product.iconUrl", target = "icon_url")
    @Mapping(source = "product.iconHoverUrl", target = "icon_hover_url")
    @Mapping(source = "product.phoneNumber", target = "phone_number")
    @Mapping(source = "product.createdAt", target = "created_at")
    ProductResponse toProductResponse(Products product, List<ProductContentResponse> contents, List<ProductInfoResponse> infos);

    ProductContentResponse toContentResponse(ProductContents content);

    ProductInfoResponse toInfoResponse(ProductInfos info);
}
