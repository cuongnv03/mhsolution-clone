package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Founders;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Socials;
import com.example.mhsolutionclone.data.response.FounderResponse;
import com.example.mhsolutionclone.data.response.SocialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FounderMapper {
    @Mapping(source = "founder.seoId", target = "seo_id")
    @Mapping(source = "founder.jobTitle", target = "job_title")
    FounderResponse toFounderResponse(Founders founder, List<SocialResponse> socials);

    SocialResponse toSocialResponse(Socials social);
}

