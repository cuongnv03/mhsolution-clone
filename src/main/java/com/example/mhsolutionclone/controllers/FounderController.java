package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.FounderResponse;
import com.example.mhsolutionclone.services.FounderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FounderController {

    private final FounderService founderService;

    @GetMapping("/founders")
    public ResponseEntity<List<FounderResponse>> getAllFounders() {
        List<FounderResponse> founders = founderService.getAllFounders();
        return ResponseEntity.ok(founders);
    }
}
