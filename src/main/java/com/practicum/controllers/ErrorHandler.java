package com.practicum.controllers;

import com.practicum.exceptions.HappinessOverflowException;
import com.practicum.exceptions.IncorrectParameterException;
import com.practicum.exceptions.UnauthorizedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(value = "com.practicum.controllers")
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleHappinessOverflow(final HappinessOverflowException e) {
        return new ErrorResponse("Осторожно, вы так избалуете питомца!",
                String.format("Текущий уровень счастья: %d.", e.getHappinessLevel()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // отлавливаем исключение IllegalArgumentException
    public ErrorResponse handleIncorrectCount(final IncorrectParameterException e) {
        // возвращаем сообщение об ошибке
        return new ErrorResponse("Ошибка с входным параметром.",
                e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse UnauthorizedUserException(final UnauthorizedUserException e) {
        return new ErrorResponse("Питомец даёт себя гладить только хозяину.",
                String.format("Владелец - %s, а пытается погладить %s.", e.getOwner(), e.getUser()));
    }
}
