package com.pixelo.health.wellplate.core.rest;

import com.pixelo.health.wellplate.core.auth.AuthUserContext;
import com.pixelo.health.wellplate.core.profile.ActiveProfileProvider;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
    private final ActiveProfileProvider activeProfileProvider;
    private final AuthUserContext authUserContext;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultResponse<String> validMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logErrorMessage(request, e);
        return ResultResponse.failedAbout(errors.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ResultResponse<String> validIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        logErrorMessage(request, e);
        return ResultResponse.failedAbout(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HandlerMethodValidationException.class})
    public ResultResponse<String> handlerMethodValidationException(HttpServletRequest request, HandlerMethodValidationException e) {
        logErrorMessage(request, e);
        return ResultResponse.failedAbout(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultResponse<String> validException(HttpServletRequest request, Exception e) {
        logErrorMessageWithStackTrace(request, e);
        return ResultResponse.failedAbout(String.format("Please try again in a momentarily : %s", e.getMessage()));
    }
    private void logErrorMessage(HttpServletRequest request, Exception e) {
        logErrorMessage(request, e, false);
    }

    private void logErrorMessageWithStackTrace(HttpServletRequest request, Exception e) {
        logErrorMessage(request, e, true);
    }

    private void logErrorMessage(HttpServletRequest request, Exception e, boolean printStackTrace) {
        var userId = authUserContext.userId();
        var errorMessage = "[UserID:" + userId + " ABOUT_REQ: "+ request.getMethod() +" "+ request.getRequestURI() +"]: " + e.getMessage();
//        var errorMessage = " ABOUT_REQ: "+ request.getMethod() +" "+ request.getRequestURI() +"]: " + e.getMessage();
        if (activeProfileProvider.isLocal() || printStackTrace) {
            log.error(errorMessage, e);
            return;
        }
        log.error(errorMessage);
    }
}
