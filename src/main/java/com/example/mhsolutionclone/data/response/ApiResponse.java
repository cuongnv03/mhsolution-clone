package com.example.mhsolutionclone.data.response;

import java.util.List;

public class ApiResponse<T> {
    private int code;
    private String message;
    private List<T> result;
}
