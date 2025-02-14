package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.mappers.banner.BannerMapper;
import com.example.mhsolution.domain.data.responses.banner.BannerResponse;
import com.example.mhsolution.repository.repositories.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    public List<BannerResponse> getAllBanners() {
        return bannerRepository.findAll()
                .stream()
                .map(bannerMapper::toResponse)
                .collect(Collectors.toList());
    }
}
