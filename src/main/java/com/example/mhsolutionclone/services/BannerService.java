package com.example.mhsolutionclone.services;

import com.example.mhsolutionclone.data.mapper.BannerMapper;
import com.example.mhsolutionclone.data.response.BannerResponse;
import com.example.mhsolutionclone.data.response.PartnerResponse;
import com.example.mhsolutionclone.repositories.BannerRepository;
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
                .map(bannerMapper::toBannerResponse)
                .collect(Collectors.toList());
    }
}
