package pl.matcodem.bankacccore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class LackOfAccountFundsException extends RuntimeException {

    public LackOfAccountFundsException(String message) {
        super(message);
    }
}
