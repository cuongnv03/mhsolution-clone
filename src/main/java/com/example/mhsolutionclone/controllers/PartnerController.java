package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.PartnerResponse;
import com.example.mhsolutionclone.services.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping("/partners")
    public ResponseEntity<List<PartnerResponse>> getAllPartners() {
        List<PartnerResponse> partners = partnerService.getAllPartners();
        return ResponseEntity.ok(partners);
    }
}

