package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Banners;
import com.example.mhsolutionclone.data.response.BannerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BannerMapper {
    @Mapping(source = "seoId", target = "seo_id")
    @Mapping(source = "srcImage", target = "src_image")
    BannerResponse toBannerResponse(Banners banner);
}
