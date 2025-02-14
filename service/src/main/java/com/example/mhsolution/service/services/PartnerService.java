package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.mappers.partner.PartnerMapper;
import com.example.mhsolution.domain.data.responses.partner.PartnerResponse;
import com.example.mhsolution.repository.repositories.PartnerRepository;
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
                .map(partnerMapper::toResponse)
                .collect(Collectors.toList());
    }
}

