package pl.matcodem.bankacccmd.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class DepositFundsCommand {

    @TargetAggregateIdentifier
    private String id;
    private BigDecimal amount;

}
