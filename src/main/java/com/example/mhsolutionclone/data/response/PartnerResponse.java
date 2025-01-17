package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerResponse {
    private Integer id;
    private String seo_id;
    private String name;
    private String avatar;
    private String src;
}

