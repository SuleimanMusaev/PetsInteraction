package com.practicum.controllers;

import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.practicum.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dogs")

public class DogsInteractionController {
    private Integer happiness = 0;
    private static final int MAX_COUNT_OF_HAPPINESS_LEVEL = 10;
    private String owner;

    @PostMapping("/owner")
    public Map<String, String> setOwner(String newOwner) {
        checkOwner(newOwner);
        owner = newOwner;
        return Map.of("owner", newOwner);
    }

    @GetMapping("/converse")
    public Map<String, String> converse() {
        checkHappiness();
        happiness += 2;
        return Map.of("talk", "Гав!");
    }

    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

    @GetMapping("/feed")
    public Map<String, Integer> feed() {
        throw new NegativeCountException(HttpStatus.NOT_IMPLEMENTED, "Метод /feed ещё не реализован.");
    }

    @PutMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count,
                                   @RequestParam(required = false) final String user) {
        if (owner == null) {
            throw new IncorrectParameterException("Необходимо установить параметр owner.");
        }
        if (!user.equals(owner)) {
            throw new UnauthorizedUserException(user, owner);
        }
        if (count == null) {
            throw new IncorrectCountException("Параметр count равен null.");
        }
        if (count <= 0) {
            throw new IncorrectCountException("Параметр count имеет отрицательное значение.");
        }

        happiness += count;
        checkHappiness();
        return Map.of("action", "Вильнул хвостом. ".repeat(count));
    }

    private void checkHappiness() {
        if (happiness > MAX_COUNT_OF_HAPPINESS_LEVEL) {
            throw new HappinessOverflowException(happiness);
        }
    }

    private void checkOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new IncorrectParameterException("Параметр newOwner равен null");
        }
    }
}
