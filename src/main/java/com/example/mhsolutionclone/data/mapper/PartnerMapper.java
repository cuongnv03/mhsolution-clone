package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Partners;
import com.example.mhsolutionclone.data.response.PartnerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
    @Mapping(source = "partner.seoId", target = "seo_id")
    PartnerResponse toPartnerResponse(Partners partner);
}
