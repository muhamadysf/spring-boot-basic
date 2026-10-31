package com.book_catalog_web.exception;

import com.book_catalog_web.dto.response.ErrorResponseDTO;
import com.book_catalog_web.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> details = new ArrayList<>();
        log.error("Exception: ", ex);
        for(ObjectError error : ex.getAllErrors()){
            details.add(error.getDefaultMessage());
        }

        ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), HttpStatus.BAD_REQUEST, "invalid data", ErrorCode.INVALID_DATA, details);

        return ResponseEntity.badRequest().body(dto);
    }


    @ExceptionHandler(exception = ResourceNotFoundException.class)
    protected ResponseEntity<ErrorResponseDTO> handleResourceNotFoundExceptionn(ResourceNotFoundException ex, WebRequest request){

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        log.error("Exception: ", ex);
        ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND, "Data Not Found", ErrorCode.DATA_NOT_FOUND, details);

        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(exception = Exception.class)
    protected ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception exception, WebRequest request){

        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());
        log.error("Exception: ", exception);
        ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, "General Exception", ErrorCode.INTERNAL_ERROR, details);

        return ResponseEntity.internalServerError().body(dto);
    }
}
