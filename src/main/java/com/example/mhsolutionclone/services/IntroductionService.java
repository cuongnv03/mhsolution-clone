package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.DataIntroduction;
import com.example.mhsolutionclone.data.mapper.IntroductionMapper;
import com.example.mhsolutionclone.data.response.*;
import com.example.mhsolutionclone.repositories.IntroductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IntroductionService {

    private final IntroductionRepository introductionRepository;
    private final IntroductionMapper introductionMapper;

    public DataIntroductionResponse getIntroductionById(int id) {
        DataIntroduction introduction = introductionRepository.findById(id);

        if (introduction == null) {
            throw new RuntimeException("Introduction not found");
        }

        List<VisionResponse> visions = introductionRepository.findVisionsByIntroductionId(id).stream()
                .map(vision -> new VisionResponse(vision.getTitle(), vision.getContent()))
                .collect(Collectors.toList());

        List<MissionResponse> missions = introductionRepository.findMissionsByIntroductionId(id).stream()
                .map(mission -> new MissionResponse(mission.getTitle(), mission.getContent()))
                .collect(Collectors.toList());

        List<CoreValueResponse> coreValues = introductionRepository.findCoreValuesByIntroductionId(id).stream()
                .map(coreValue -> new CoreValueResponse(coreValue.getTitle(), coreValue.getContent()))
                .collect(Collectors.toList());

        List<CompetitiveResponse> competitives = introductionRepository.findCompetitivesByIntroductionId(id).stream()
                .map(competitive -> new CompetitiveResponse(competitive.getTitle(), competitive.getContent()))
                .collect(Collectors.toList());

        return introductionMapper.fromPojoToResponse(
                introduction,
                visions,
                missions,
                coreValues,
                competitives
        );
    }
}
