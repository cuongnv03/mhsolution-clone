package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Introduction;
import com.example.mhsolutionclone.data.mapper.IntroductionMapper;
import com.example.mhsolutionclone.data.response.*;
import com.example.mhsolutionclone.repositories.IntroductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntroductionService {

    private final IntroductionRepository introductionRepository;
    private final IntroductionMapper introductionMapper;

    public IntroductionResponse getIntroductionById(int id) {
        Introduction introduction = introductionRepository.findById(id);

        if (introduction == null) {
            throw new RuntimeException("Introduction not found");
        }

        return introductionMapper.toIntroductionResponse(introduction);
    }
}
