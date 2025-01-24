package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.IntroductionResponse;
import com.example.mhsolutionclone.services.IntroductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/introduction")
@RequiredArgsConstructor
public class IntroductionController {

    private final IntroductionService introductionService;

    @GetMapping
    public ResponseEntity<IntroductionResponse> getIntroductionById() {
        IntroductionResponse response = introductionService.getIntroductionById(1);
        return ResponseEntity.ok(response);
    }
}
