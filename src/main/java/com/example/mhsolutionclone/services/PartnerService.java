package com.example.mhsolutionclone.services;

import com.example.mhsolutionclone.data.mapper.PartnerMapper;
import com.example.mhsolutionclone.data.response.PartnerResponse;
import com.example.mhsolutionclone.repositories.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    public List<PartnerResponse> getAllPartners() {
        return partnerRepository.findAll()
                .stream()
                .map(partnerMapper::toPartnerResponse)
                .collect(Collectors.toList());
    }
}

