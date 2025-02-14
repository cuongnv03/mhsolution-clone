package com.example.mhsolution.domain.data.models.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Competitive {
    private String title;
    private String content;
    private String src;
}
