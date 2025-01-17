package com.example.mhsolutionclone.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {
    private String name;
    private String operation;
    private String value;
}
