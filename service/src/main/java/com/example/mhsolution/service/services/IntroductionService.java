package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.tables.pojos.Introduction;
import com.example.mhsolution.domain.data.responses.introduction.IntroductionResponse;
import com.example.mhsolution.domain.data.mappers.introduction.IntroductionMapper;
import com.example.mhsolution.repository.repositories.IntroductionRepository;
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

        return introductionMapper.toResponse(introduction);
    }
}
