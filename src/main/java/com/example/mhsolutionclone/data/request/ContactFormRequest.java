package com.example.mhsolutionclone.data.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactFormRequest {
    @NotBlank(message = "Tên không được phép để trống")
    private String full_name;

    @Pattern(
            regexp = "^\\d{10}$",
            message = "Số điện thoại không hợp lệ"
    )
    @NotBlank
    private String phone_number;

    @Email(message = "Email không hợp lệ")
    @NotBlank
    private String email;

    private String message;
}

