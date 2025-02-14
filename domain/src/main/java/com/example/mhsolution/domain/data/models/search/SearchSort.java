package com.example.mhsolution.domain.data.models.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchSort {
    public enum SortDirection {
        ASC, DESC
    }
    private String property;
    private SortDirection direction;
}
