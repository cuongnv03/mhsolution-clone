package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntroductionResponse {
    private Integer id;
    private String title;
    private String content;
    private List<VisionResponse> visions;
    private List<MissionResponse> missions;
    private List<CoreValueResponse> coreValues;
    private List<CompetitiveResponse> competitives;
    private String cover_url;
}
