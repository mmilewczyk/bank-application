package pl.matcodem.accountcmd.commands;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.matcodem.accountcore.models.User;

@Data
@Builder
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @Valid
    @NotNull(message = "no user details were supplied")
    private User user;
}
