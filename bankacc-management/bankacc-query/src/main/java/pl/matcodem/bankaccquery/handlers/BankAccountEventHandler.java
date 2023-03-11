package pl.matcodem.bankaccquery.handlers;

import pl.matcodem.bankacccore.events.AccountClosedEvent;
import pl.matcodem.bankacccore.events.AccountOpenedEvent;
import pl.matcodem.bankacccore.events.FundsDepositedEvent;
import pl.matcodem.bankacccore.events.FundsWithdrawnEvent;

public interface BankAccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(AccountClosedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(FundsDepositedEvent event);
}
