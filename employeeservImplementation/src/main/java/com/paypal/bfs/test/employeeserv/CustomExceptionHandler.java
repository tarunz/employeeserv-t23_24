package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler to handle the exceptions and map to correct http codes.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Map<Class, HttpStatus> errorsToHttpStatusMap = new HashMap<>();

    static {
        errorsToHttpStatusMap.put(EmployeeNotFoundException.class, HttpStatus.NOT_FOUND);
        errorsToHttpStatusMap.put(InvalidRequestException.class, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleEx(Exception e, WebRequest request) {
        return new ResponseEntity<>(
                e.getMessage(),
                errorsToHttpStatusMap.getOrDefault(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
