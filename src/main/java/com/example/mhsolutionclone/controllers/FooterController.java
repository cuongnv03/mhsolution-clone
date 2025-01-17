package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.FooterResponse;
import com.example.mhsolutionclone.services.FooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FooterController {

    private final FooterService footerService;

    @GetMapping("/footer")
    public ResponseEntity<FooterResponse> getFooter() {
        FooterResponse footer = footerService.getFooter();
        return ResponseEntity.ok(footer);
    }
}

