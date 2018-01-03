package com.tristanperry.microservices.businesslogicapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find requested resource")
public class NotFoundException extends RuntimeException {

}
