package kiszel.daniel.webshop.util;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUnmatchedExceptions(Exception ex) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

        if (responseStatus == null || HttpStatus.INTERNAL_SERVER_ERROR.equals(responseStatus.code())) {
            // for instance, we may only want 500s to be an empty body
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Map<String, String> errors = new HashMap<>();
        errors.put("error",ex.getMessage());
        return new ResponseEntity<>(errors, responseStatus.code());
    }
}