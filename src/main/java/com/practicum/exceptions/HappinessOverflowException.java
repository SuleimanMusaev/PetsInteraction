package com.practicum.exceptions;

public class HappinessOverflowException extends RuntimeException {
    private Integer happinessLevel;

    public Integer getHappinessLevel() {
        return happinessLevel;
    }

    public HappinessOverflowException(Integer happinessLevel) {
        this.happinessLevel = happinessLevel;
    }
}
