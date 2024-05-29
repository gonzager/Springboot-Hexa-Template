package com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;
@Data
@AllArgsConstructor
public class BadRequestErrors {
    private HttpStatus status;
    private List<String> errors;
}