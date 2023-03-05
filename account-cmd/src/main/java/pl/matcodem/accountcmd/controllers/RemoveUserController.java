package pl.matcodem.accountcmd.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.matcodem.accountcmd.commands.RemoveUserCommand;
import pl.matcodem.accountcore.dto.BaseResponse;

import static org.springframework.http.HttpStatus.*;
import static pl.matcodem.accountcore.configuration.EndpointConstants.USERS_ENDPOINT_V1;

@Slf4j
@RestController
@RequestMapping(path = USERS_ENDPOINT_V1)
@RequiredArgsConstructor
public class RemoveUserController {

    private final CommandGateway commandGateway;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> removeUserById(@PathVariable("id") String id) {
        try {
            commandGateway.send(new RemoveUserCommand(id));
            return new ResponseEntity<>(new BaseResponse("User successfully removed!"), OK);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Error while processing remove user request for id - " + id;
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
