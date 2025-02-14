package com.example.mhsolution.domain.data.models.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Social {
    private String type;
    private String src;
    private String icon;
}
