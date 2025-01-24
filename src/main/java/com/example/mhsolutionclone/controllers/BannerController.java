package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.BannerResponse;
import com.example.mhsolutionclone.services.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping
    public ResponseEntity<List<BannerResponse>> getAllBanners() {
        List<BannerResponse> banners = bannerService.getAllBanners();
        return ResponseEntity.ok(banners);
    }
}
