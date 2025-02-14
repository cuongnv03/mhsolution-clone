package com.example.mhsolution.domain.data.requests.contact_form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ContactFormRequest {
    @NotBlank(message = "Tên không được phép để trống")
    private String fullName;

    @Pattern(
            regexp = "^\\d{10}$",
            message = "Số điện thoại không hợp lệ"
    )
    @NotBlank
    private String phoneNumber;

    @Email(message = "Email không hợp lệ")
    @NotBlank
    private String email;

    private String message;
}

