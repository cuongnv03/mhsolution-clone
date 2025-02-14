package com.example.mhsolution.domain.data.requests.contact;

import com.example.mhsolution.domain.data.models.type.Social;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactRequest {
    private String address;
    private String email;
    private String phoneNumber;
    private List<Social> socials;
}
