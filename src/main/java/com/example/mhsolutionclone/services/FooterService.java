package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.DataFooter;
import com.example.mhsolutionclone.data.mapper.FooterMapper;
import com.example.mhsolutionclone.data.response.FooterResponse;
import com.example.mhsolutionclone.data.response.FooterSocialResponse;
import com.example.mhsolutionclone.repositories.FooterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FooterService {

    private final FooterRepository footerRepository;
    private final FooterMapper footerMapper;

    public FooterResponse getFooter() {
        DataFooter footer = footerRepository.findFooter();
        if (footer == null) {
            throw new RuntimeException("Footer not found");
        }

        List<FooterSocialResponse> socials = footerRepository.findSocialsByFooterId(footer.getId()).stream()
                .map(footerMapper::toFooterSocialResponse)
                .collect(Collectors.toList());

        return footerMapper.toFooterResponse(footer, socials);
    }
}

