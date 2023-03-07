package pl.matcodem.accountcmd.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.matcodem.accountcore.models.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @Valid
    @NotNull(message = "no user details were supplied")
    private User user;
}
