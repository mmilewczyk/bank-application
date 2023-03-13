package pl.matcodem.bankacccmd.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.matcodem.bankacccmd.commands.WithdrawFundsCommand;
import pl.matcodem.bankacccore.dto.BaseResponse;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import static pl.matcodem.bankacccore.configuration.EndpointConstants.BANK_ACCOUNT_ENDPOINT_V1;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = BANK_ACCOUNT_ENDPOINT_V1)
public class WithdrawFundsController {

    private final CommandGateway commandGateway;

    @PutMapping("/withdraw/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable("id") String id, @Valid @RequestBody WithdrawFundsCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command);
            var message = "Funds successfully withdrawn!";
            return new ResponseEntity<>(new BaseResponse(message), OK);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Error while processing withdraw funds request for id - " + id;
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
