package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String seo_id;
    private String title;
    private List<ProductContentResponse> contents;
    private String short_content;
    private String website;
    private List<ProductInfoResponse> infos;
    private String info_url;
    private String cover_url;
    private String icon_url;
    private String icon_hover_url;
    private String phone_number;
    private LocalDate created_at;
}
