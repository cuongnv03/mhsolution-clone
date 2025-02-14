package com.example.mhsolution.domain.data.requests.product;

import com.example.mhsolution.domain.data.models.type.Info;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {
    private String title;
    private List<Info> contents;
    private String shortContent;
    private String website;
    private List<Info> infos;
    private String infoUrl;
    private String coverUrl;
    private String iconUrl;
    private String iconHoverUrl;
    private String phoneNumber;
    private LocalDate createdAt;
}
