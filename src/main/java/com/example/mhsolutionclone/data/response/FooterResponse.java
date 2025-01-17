package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FooterResponse {
    private Integer id;
    private String seo_id;
    private String address;
    private String email;
    private String phone_number;
    private List<FooterSocialResponse> socials;
}

