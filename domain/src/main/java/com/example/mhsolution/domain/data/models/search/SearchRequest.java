package com.example.mhsolution.domain.data.models.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private int page = 1;                // Mặc định là 1
    private int limit = 8;               // Mặc định là 8
    private SearchSort sorts;
    private List<SearchFilter> filters = Collections.emptyList(); // Mặc định là rỗng
}
