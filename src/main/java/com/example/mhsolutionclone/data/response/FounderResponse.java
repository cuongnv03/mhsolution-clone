package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FounderResponse {
    private Integer id;
    private String seo_id;
    private String name;
    private String avatar;
    private String job_title;
    private String about;
    private List<SocialResponse> socials;
}
