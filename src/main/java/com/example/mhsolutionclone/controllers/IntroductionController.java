package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.DataIntroductionResponse;
import com.example.mhsolutionclone.services.IntroductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IntroductionController {

    private final IntroductionService introductionService;

    @GetMapping("/introduction")
    public ResponseEntity<DataIntroductionResponse> getIntroductionById() {
        DataIntroductionResponse response = introductionService.getIntroductionById(1);
        return ResponseEntity.ok(response);
    }
}
