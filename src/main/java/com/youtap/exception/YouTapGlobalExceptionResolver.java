package com.youtap.exception;

import com.youtap.dto.Response;
import com.youtap.enums.ResponseCode;
import com.youtap.utils.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class YouTapGlobalExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {YouTapServiceException.class})
    protected ResponseEntity<Response> handleApiError(YouTapServiceException exception) {
        log.error("YouTap user service exception while executing request: {}", exception);
        return new ResponseEntity<>(RestUtils.getApiResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    protected ResponseEntity<Response<Void>> handleGenericError(RuntimeException exception) {
        log.error("Generic exception while executing request: {}", exception);
        return new ResponseEntity<>(RestUtils.getApiResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {

        log.error("YouTap resource not found exception : {}", exception);
        return new ResponseEntity<>(RestUtils.getNotFoundApiResponse(exception), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final String firstError = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst().orElse("");
        log.error("Validation error occurred: {}", firstError);
        return new ResponseEntity<>(RestUtils.getApiResponse(ResponseCode.BAD_REQUEST, firstError),
                HttpStatus.BAD_REQUEST);
    }



    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        List<String> details = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("Content Type: ");
        builder.append(exception.getContentType());
        builder.append(".");
        builder.append(" Media type is not supported. Supported media types are ");
        exception.getSupportedMediaTypes().forEach(t -> builder.append(t).append(" "));

        details.add(builder.toString());

        log.error("Media type is not supported: {}", details);
        return new ResponseEntity<>(RestUtils.getApiResponse(ResponseCode.BAD_REQUEST, details.get(0)),
                HttpStatus.BAD_REQUEST);
    }
}
