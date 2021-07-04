package com.itmo.goblinslayersystemserver.models.exceptions;

public class FilesStorageException extends RuntimeException {
    public FilesStorageException(String message) {
        super(message);
    }

    public FilesStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
