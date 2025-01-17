package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactFormResponse {
    private Integer id;
    private String full_name;
    private String phone_number;
    private String email;
    private String message;
    private LocalDateTime submitted_at;
}

