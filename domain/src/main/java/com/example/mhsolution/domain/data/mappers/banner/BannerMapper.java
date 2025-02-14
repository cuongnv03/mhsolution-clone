package com.example.mhsolution.domain.data.mappers.banner;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.requests.banner.BannerRequest;
import com.example.mhsolution.domain.data.tables.pojos.Banners;
import com.example.mhsolution.domain.data.responses.banner.BannerResponse;
import org.mapstruct.*;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public interface BannerMapper extends BaseMapper<BannerRequest, BannerResponse, Banners> {
    @AfterMapping
    default void afterResponse(Banners pojo, @MappingTarget BannerResponse response) {
        final String title = response.getTitle();
        final String id = String.valueOf(response.getId());
        response.setSeoId(generateSeoId(title, id));
    }
}
