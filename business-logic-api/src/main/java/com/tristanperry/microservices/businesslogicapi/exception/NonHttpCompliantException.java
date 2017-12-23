package com.tristanperry.microservices.businesslogicapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason="Something went wrong, this should probably return 500 not 502 but this is just a demo app ^^")
public class NonHttpCompliantException extends RuntimeException {

}
