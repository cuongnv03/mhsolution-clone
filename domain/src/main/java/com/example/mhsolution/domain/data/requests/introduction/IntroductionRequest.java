package com.example.mhsolution.domain.data.requests.introduction;

import com.example.mhsolution.domain.data.models.type.Competitive;
import com.example.mhsolution.domain.data.models.type.CoreValue;
import com.example.mhsolution.domain.data.models.type.Mission;
import com.example.mhsolution.domain.data.models.type.Vision;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntroductionRequest {
    private String title;
    private String content;
    private List<Vision> visions;
    private List<Mission> missions;
    private List<CoreValue> coreValues;
    private List<Competitive> competitives;
    private String coverUrl;
}
