package com.spring.resturant.config;

import com.spring.resturant.dto.ExceptionDto;
import com.spring.resturant.dto.ExceptionDto.ExceptionData;
import com.spring.resturant.service.bundel.LocalBundleMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class ExceptionHandlerConfig {

    private final LocalBundleMessageService localBundleMessageService;

    public ExceptionHandlerConfig(LocalBundleMessageService localBundleMessageService) {
        this.localBundleMessageService = localBundleMessageService;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .messageAr(localBundleMessageService.getArabicMessage(ex.getMessage()))
                .messageEn(localBundleMessageService.getEnglishMessage(ex.getMessage()))
                .data(ExceptionData.builder()
                        .timestamp(localBundleMessageService.getCurrentTimestamp())
                        .path(request.getRequestURI())
                        .build())
                .build();


        return ResponseEntity.ok(exceptionDto);
    }
}

