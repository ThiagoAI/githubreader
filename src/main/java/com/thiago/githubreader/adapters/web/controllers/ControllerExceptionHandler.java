package com.thiago.githubreader.adapters.web.controllers;


import com.thiago.githubreader.application.exceptions.FailedToGetGitHubFilesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Void> validationError(
            ValidationException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    protected ResponseEntity<Void> failedToGetFilesException(
            FailedToGetGitHubFilesException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

