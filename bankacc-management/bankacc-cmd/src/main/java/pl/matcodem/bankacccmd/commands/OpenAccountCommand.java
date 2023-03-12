package pl.matcodem.bankacccmd.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.matcodem.bankacccore.models.AccountType;

import javax.annotation.Nonnull;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class OpenAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "No account holder ID was supplied")
    private String accountHolderId;

    @NotNull(message = "No account type was supplied")
    private AccountType accountType;

    @Min(value = 1, message = "Opening account balance must be greater than zero.")
    private BigDecimal openingBalance;
}
