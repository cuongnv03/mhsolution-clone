package com.example.mhsolutionclone.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissionResponse {
    private String title;
    private String content;
}