package pl.matcodem.bankaccquery.handlers;

import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import pl.matcodem.bankacccore.events.AccountClosedEvent;
import pl.matcodem.bankacccore.events.AccountOpenedEvent;
import pl.matcodem.bankacccore.events.FundsDepositedEvent;
import pl.matcodem.bankacccore.events.FundsWithdrawnEvent;
import pl.matcodem.bankacccore.models.BankAccount;
import pl.matcodem.bankaccquery.repositories.BankAccountRepository;

@Service
@AllArgsConstructor
@ProcessingGroup(value = "bankaccount-group")
public class BankAccountEventHandlerImpl implements BankAccountEventHandler {

    private final BankAccountRepository bankAccountRepository;

    @Override
    @EventHandler
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .accountType(event.getAccountType())
                .creationDate(event.getCreationDate())
                .balance(event.getOpeningBalance())
                .build();
        bankAccountRepository.save(bankAccount);
    }

    @Override
    @EventHandler
    public void on(AccountClosedEvent event) {
        bankAccountRepository.deleteById(event.getId());
    }

    @Override
    @EventHandler
    public void on(FundsWithdrawnEvent event) {
        var bankAccount = bankAccountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) return;
        var foundBankAccount = bankAccount.get();
        foundBankAccount.setBalance(foundBankAccount.getBalance());
        bankAccountRepository.save(foundBankAccount);
    }

    @Override
    @EventHandler
    public void on(FundsDepositedEvent event) {
        var bankAccount = bankAccountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) return;
        bankAccount.get().setBalance(event.getBalance());
        bankAccountRepository.save(bankAccount.get());
    }
}
