package uz.elmurodov.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private final HttpStatus status;

    public NotFoundException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public NotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
