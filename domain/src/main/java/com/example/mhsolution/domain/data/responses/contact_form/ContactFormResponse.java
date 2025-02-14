package com.example.mhsolution.domain.data.responses.contact_form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactFormResponse {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String message;
    private LocalDateTime submittedAt;
}

