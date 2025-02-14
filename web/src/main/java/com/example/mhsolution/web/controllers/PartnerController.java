package com.example.mhsolution.web.controllers;

import com.example.mhsolution.domain.data.responses.partner.PartnerResponse;
import com.example.mhsolution.service.services.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping
    public ResponseEntity<List<PartnerResponse>> getAllPartners() {
        List<PartnerResponse> partners = partnerService.getAllPartners();
        return ResponseEntity.ok(partners);
    }
}

