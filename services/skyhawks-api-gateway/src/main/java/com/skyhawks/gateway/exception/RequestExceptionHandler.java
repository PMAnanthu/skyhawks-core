package com.skyhawks.gateway.exception;

import com.skyhawks.dtos.resonses.Response;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (!details.contains(error.getDefaultMessage()))
                details.add(error.getDefaultMessage());
        }

        Response response = new Response();
        response.setMessage("Validation Error");
        response.setErrors(details);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Response response = new Response();
        response.setMessage("Server error");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        response.setErrors(details);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleAllExceptions(DataIntegrityViolationException ex, WebRequest request) {
        Response response = new Response();
        response.setMessage("Data already exists");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        response.setErrors(details);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleAllExceptions(ConstraintViolationException ex, WebRequest request) {
        Response response = new Response();
        response.setMessage("Data already exists");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        response.setErrors(details);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SchoolNotFountException.class)
    public final ResponseEntity<Object> handleAllExceptions(SchoolNotFountException ex, WebRequest request) {
        Response response = new Response();
        response.setMessage("School code is wrong");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        response.setErrors(details);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentNotFountException.class)
    public final ResponseEntity<Object> handleAllExceptions(StudentNotFountException ex, WebRequest request) {
        Response response = new Response();
        response.setMessage("Admission number is wrong");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        response.setErrors(details);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public final ResponseEntity<Object> handleAllExceptions(MalformedJwtException ex, WebRequest request) {
        Response response = new Response();
        response.setMessage("JWT Token error.");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        response.setErrors(details);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
