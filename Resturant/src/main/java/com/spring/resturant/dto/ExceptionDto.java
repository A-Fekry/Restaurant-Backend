package com.spring.resturant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
    private int status;
    private String messageAr;
    private String messageEn;
    private ExceptionData data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExceptionData {
        private LocalDateTime timestamp;
        private String path;
    }
}

