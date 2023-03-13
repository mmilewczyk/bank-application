package pl.matcodem.bankacccmd.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.matcodem.bankacccmd.commands.CloseAccountCommand;
import pl.matcodem.bankacccore.dto.BaseResponse;

import static org.springframework.http.HttpStatus.*;
import static pl.matcodem.bankacccore.configuration.EndpointConstants.BANK_ACCOUNT_ENDPOINT_V1;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = BANK_ACCOUNT_ENDPOINT_V1)
public class CloseAccountController {

    private final CommandGateway commandGateway;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable("id") String id) {
        try {
            commandGateway.send(new CloseAccountCommand(id));
            var message = "Account successfully closed!";
            return new ResponseEntity<>(new BaseResponse(message), OK);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Error while processing close account request for id - " + id;
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
