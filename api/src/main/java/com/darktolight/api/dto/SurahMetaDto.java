package com.darktolight.api.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurahMetaDto {
    private int number;
    private String name;
    private String englishName;
    private String englishNameTranslation;
    private int numberOfAyahs;
    private String revelationType;
}