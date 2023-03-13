package pl.matcodem.bankacccmd.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Builder
public class DepositFundsCommand {

    @TargetAggregateIdentifier
    private String id;
    @Min(value = 1)
    private BigDecimal amount;

}
