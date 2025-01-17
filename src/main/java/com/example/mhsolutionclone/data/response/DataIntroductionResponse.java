package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataIntroductionResponse {
    private Integer id;
    private String title;
    private String content;
    private String cover_url;
    private List<VisionResponse> visions;
    private List<MissionResponse> missions;
    private List<CoreValueResponse> coreValues;
    private List<CompetitiveResponse> competitives;
}
