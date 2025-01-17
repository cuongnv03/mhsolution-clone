package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Founders;
import com.example.mhsolutionclone.data.mapper.FounderMapper;
import com.example.mhsolutionclone.data.response.FounderResponse;
import com.example.mhsolutionclone.data.response.SocialResponse;
import com.example.mhsolutionclone.repositories.FounderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FounderService {

    private final FounderRepository founderRepository;
    private final FounderMapper founderMapper;

    public List<FounderResponse> getAllFounders() {
        List<Founders> founders = founderRepository.findAll();
        return founders.stream()
                .map(founder -> {
                    List<SocialResponse> socials = founderRepository.findSocialsByFounderId(founder.getId())
                            .stream()
                            .map(founderMapper::toSocialResponse)
                            .collect(Collectors.toList());
                    return founderMapper.toFounderResponse(founder, socials);
                })
                .collect(Collectors.toList());
    }
}

