package com.example.mhsolution.domain.data.mappers.partner;

import com.example.mhsolution.domain.data.mappers.BaseMapper;
import com.example.mhsolution.domain.data.requests.partner.PartnerRequest;
import com.example.mhsolution.domain.data.tables.pojos.Partners;
import com.example.mhsolution.domain.data.responses.partner.PartnerResponse;
import org.mapstruct.*;

import static com.example.mhsolution.common.utils.SeoUtils.generateSeoId;

@Mapper(componentModel = "spring")
public interface PartnerMapper extends BaseMapper<PartnerRequest, PartnerResponse, Partners> {
    @AfterMapping
    default void afterResponse(Partners pojo, @MappingTarget PartnerResponse response) {
        final String title = response.getName();
        final String id = String.valueOf(response.getId());
        response.setSeoId(generateSeoId(title, id));
    }
}
