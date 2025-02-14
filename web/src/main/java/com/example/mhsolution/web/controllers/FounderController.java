package com.example.mhsolution.web.controllers;

import com.example.mhsolution.domain.data.responses.founder.FounderResponse;
import com.example.mhsolution.service.services.FounderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/founders")
@RequiredArgsConstructor
public class FounderController {

    private final FounderService founderService;

    @GetMapping
    public ResponseEntity<List<FounderResponse>> getAllFounders() {
        List<FounderResponse> founders = founderService.getAllFounders();
        return ResponseEntity.ok(founders);
    }
}
