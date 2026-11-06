package com.darktolight.api.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuranResponseDto<T> {
    private int code;
    private String status;
    private T data;
}