package org.dynamics360.org.ecomapp.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handlerGeneralExceptions(Exception exception,
                                                                HttpServletRequest request,
                                                                WebRequest webRequest){
        return handleGenericException(exception, request, webRequest);
    }

    private ResponseEntity<ApiErrorDto> handleGenericException(
            Exception exception,
            HttpServletRequest request,
            WebRequest webRequest) {

        ApiErrorDto dto = new ApiErrorDto(
                "Unexpected Error",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString()
        );

        return ResponseEntity.internalServerError().body(dto);

    }
}
