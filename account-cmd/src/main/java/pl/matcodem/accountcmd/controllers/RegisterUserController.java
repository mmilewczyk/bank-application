package pl.matcodem.accountcmd.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.matcodem.accountcmd.commands.RegisterUserCommand;
import pl.matcodem.accountcmd.dto.RegisterUserResponse;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/user")
@RequiredArgsConstructor
public class RegisterUserController {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserCommand command) {
        String id = randomUUID().toString();
        command.setId(id);
        try {
            commandGateway.sendAndWait(command);
            var message = "User successfully registered!";
            return new ResponseEntity<>(new RegisterUserResponse(id, message), CREATED);
        } catch (Exception e) {
            log.error(e.toString(), e);
            var safeErrorMessage = "Error while processing register user request for id - " + id;
            return new ResponseEntity<>(new RegisterUserResponse(id, safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
