package pl.matcodem.bankacccmd.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.matcodem.bankacccmd.commands.OpenAccountCommand;
import pl.matcodem.bankacccmd.dto.OpenBankAccountResponse;

import javax.validation.Valid;

import static java.util.UUID.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static pl.matcodem.bankacccore.configuration.EndpointConstants.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = BANK_ACCOUNT_ENDPOINT_V1)
public class OpenAccountController {

    private final CommandGateway commandGateway;

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<OpenBankAccountResponse> openBankAccount(@Valid @RequestBody OpenAccountCommand command) {
        var id = randomUUID().toString();
        command.setId(id);
        try {
            commandGateway.sendAndWait(command);
            var message = "Bank account successfully opened!";
            return new ResponseEntity<>(new OpenBankAccountResponse(id, message), CREATED);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Error while processing open bank account request for id - " + id;
            return new ResponseEntity<>(new OpenBankAccountResponse(id, safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
