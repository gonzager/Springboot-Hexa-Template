package com.fluxit.microexa.template.infraestructure.adapters.in.rest.exceptions;

import org.springframework.http.HttpStatus;
import java.util.List;
public record BadRequestErrors(HttpStatus status, List<String> errors) {
}