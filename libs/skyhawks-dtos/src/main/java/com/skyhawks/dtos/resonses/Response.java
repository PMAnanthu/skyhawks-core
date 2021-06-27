package com.skyhawks.dtos.resonses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response {
    private String message;
    private List<String> errors;
}