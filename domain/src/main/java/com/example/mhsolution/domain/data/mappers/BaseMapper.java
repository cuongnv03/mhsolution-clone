package com.example.mhsolution.domain.data.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;

public interface BaseMapper<Request, Response, Pojo> {
    @Named("toPOJO")
    Pojo toPOJO(Request request);

    @IterableMapping(qualifiedByName = "toPOJO")
    List<Pojo> toPOJOs(List<Request> requests);

    @Named("toResponse")
    Response toResponse(Pojo pojo);

    @IterableMapping(qualifiedByName = "toResponse")
    List<Response> toResponses(List<Pojo> pojos);
}
