package com.practicum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NegativeCountException extends ResponseStatusException {

    public NegativeCountException(HttpStatus status) {
        super(status);
    }

    public NegativeCountException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public NegativeCountException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
