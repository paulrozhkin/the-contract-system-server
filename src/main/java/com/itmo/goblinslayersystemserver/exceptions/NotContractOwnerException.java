package com.itmo.goblinslayersystemserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotContractOwnerException extends RuntimeException {
    public NotContractOwnerException() {

    }

    public NotContractOwnerException(String messageException) {
        super(messageException);
    }
}
