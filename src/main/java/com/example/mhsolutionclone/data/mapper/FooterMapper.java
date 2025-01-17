package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.DataFooter;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.FooterSocials;
import com.example.mhsolutionclone.data.response.FooterResponse;
import com.example.mhsolutionclone.data.response.FooterSocialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FooterMapper {
    @Mapping(source = "footer.seoId", target = "seo_id")
    @Mapping(source = "footer.phoneNumber", target = "phone_number")
    FooterResponse toFooterResponse(DataFooter footer, List<FooterSocialResponse> socials);

    FooterSocialResponse toFooterSocialResponse(FooterSocials social);
}

