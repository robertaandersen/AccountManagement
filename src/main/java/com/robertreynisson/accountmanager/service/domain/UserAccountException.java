package com.robertreynisson.accountmanager.service.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class UserAccountException extends Throwable {


    public static class APIError extends RuntimeException {

        public APIError() {
        }

        public APIError(String s) {
            super(s);
        }

    }


    @ExceptionHandler(InternalError.class)
    protected ResponseEntity<Object> handleInternalError(InternalError ex) {
        return new ResponseEntity<>(ex.getMessage(), INTERNAL_SERVER_ERROR);
    }

    public static class InternalError extends APIError {
        public InternalError() {
            super("Oops... seems we ran into an unexpected error");
        }

        public InternalError(String s) {
            super(s);
        }
    }


    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequest ex) {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }


    public static class BadRequest extends APIError {
        public BadRequest() {
            super("Unable to process this request");
        }

        public BadRequest(String s) {
            super(s);
        }
    }

    @ExceptionHandler(NotFound.class)
    protected ResponseEntity<Object> handleNotFound(NotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    public static class NotFound extends APIError {
        public NotFound(String s) {
            super(s);
        }

        public NotFound() {
            super();

        }
    }
}
