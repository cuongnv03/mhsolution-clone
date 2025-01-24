package com.example.mhsolutionclone.data.mapper;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Seo;
import com.example.mhsolutionclone.data.response.SeoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SeoMapper {
    @Mapping(source = "keywords", target = "keywords", qualifiedByName = "arrayToString")
    SeoResponse toSeoResponse(Seo seo);

    @Named("arrayToString")
    default String arrayToString(String[] value) {
        if (value == null) {
            return ""; // Return an empty string if the array is null
        }
        return String.join(", ", value);
    }
}
