package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    private Integer id;
    private String seo_id;
    private SeoResponse seo;
    private String title;
    private String content;
    private Integer category_new_id;
    private CategoryResponse category;
    private LocalDate created_at;
    private String cover_url;
    private Integer is_highlights;
}
