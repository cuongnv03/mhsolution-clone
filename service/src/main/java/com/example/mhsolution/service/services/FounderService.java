package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.tables.pojos.Founders;
import com.example.mhsolution.domain.data.mappers.founder.FounderMapper;
import com.example.mhsolution.domain.data.responses.founder.FounderResponse;
import com.example.mhsolution.repository.repositories.FounderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FounderService {

    private final FounderRepository founderRepository;
    private final FounderMapper founderMapper;

    public List<FounderResponse> getAllFounders() {
        List<Founders> founders = founderRepository.findAll();
        return founders.stream()
                .map(founderMapper::toResponse)
                .collect(Collectors.toList());
    }
}

