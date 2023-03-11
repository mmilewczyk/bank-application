package pl.matcodem.bankacccmd.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.matcodem.bankacccore.models.AccountType;

import java.math.BigDecimal;

@Data
@Builder
public class OpenAccountCommand {

    @TargetAggregateIdentifier
    private String id;
    private String accountHolderId;
    private AccountType accountType;
    private BigDecimal openingBalance;
}
