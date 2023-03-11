package pl.matcodem.bankacccmd.aggregates;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import pl.matcodem.bankacccmd.commands.DepositFundsCommand;
import pl.matcodem.bankacccmd.commands.OpenAccountCommand;
import pl.matcodem.bankacccmd.commands.WithdrawFundsCommand;
import pl.matcodem.bankacccore.events.AccountClosedEvent;
import pl.matcodem.bankacccore.events.AccountOpenedEvent;
import pl.matcodem.bankacccore.events.FundsDepositedEvent;
import pl.matcodem.bankacccore.events.FundsWithdrawnEvent;
import pl.matcodem.bankacccore.exceptions.LackOfAccountFundsException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Aggregate
@NoArgsConstructor
public class BankAccountAggregate {

    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private BigDecimal balance;

    @CommandHandler
    public BankAccountAggregate(OpenAccountCommand command) {
        var event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolderId(command.getAccountHolderId())
                .accountType(command.getAccountType())
                .creationDate(LocalDate.now())
                .openingBalance(command.getOpeningBalance())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event) {
        this.id = event.getId();
        this.accountHolderId = event.getAccountHolderId();
        this.balance = event.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command) {
        BigDecimal amount = command.getAmount();
        var event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(balance.add(amount))
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event) {
        balance = balance.add(event.getAmount());
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand command) {
        BigDecimal amount = command.getAmount();
        if (!isEnoughtMeansToWithdraw(amount)) {
            throw new LackOfAccountFundsException("Account doesn't possess enough means to withdraw this amount of funds");
        }
        var event = FundsWithdrawnEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(balance.subtract(amount))
                .build();
        AggregateLifecycle.apply(event);
    }

    private boolean isEnoughtMeansToWithdraw(BigDecimal amount) {
        return balance.subtract(amount).doubleValue() > 0;
    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event) {
        balance = balance.subtract(event.getAmount());
    }

    @CommandHandler
    public void handle(OpenAccountCommand command) {
        AggregateLifecycle.apply(new AccountClosedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}

