package pl.matcodem.bankaccquery.handlers;

import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import pl.matcodem.bankaccquery.dto.AccountLookupResponse;
import pl.matcodem.bankaccquery.queries.FindAccountByHolderIdQuery;
import pl.matcodem.bankaccquery.queries.FindAccountByIdQuery;
import pl.matcodem.bankaccquery.queries.FindAccountsWithBalanceQuery;
import pl.matcodem.bankaccquery.queries.FindAllAccountsQuery;
import pl.matcodem.bankaccquery.repositories.BankAccountRepository;

import static java.lang.String.format;
import static pl.matcodem.bankaccquery.dto.EqualityType.GREATER_THAN;

@Service
@AllArgsConstructor
public class BankAccountQueryHandlerImpl implements BankAccountQueryHandler {

    private final BankAccountRepository bankAccountRepository;

    private static final String SUCCESSFULL_SEARCH_RESPONSE_MESSAGE = "Bank accounts successfully returned!";
    private static final String FAILED_SEARCH_RESPONSE_MESSAGE = "No bank account found for id '%s'.";

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        return bankAccountRepository.findById(query.getAccountId())
                .map(account -> new AccountLookupResponse(SUCCESSFULL_SEARCH_RESPONSE_MESSAGE, account))
                .orElseGet(() -> new AccountLookupResponse(format(FAILED_SEARCH_RESPONSE_MESSAGE, query.getAccountId())));
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
        return bankAccountRepository.findBankAccountByAccountHolderId(query.getAccountHolderId())
                .map(account -> new AccountLookupResponse(SUCCESSFULL_SEARCH_RESPONSE_MESSAGE, account))
                .orElseGet(() -> new AccountLookupResponse(format(FAILED_SEARCH_RESPONSE_MESSAGE, query.getAccountHolderId())));
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query) {
        boolean isLookingForGreater = query.getEqualityType().equals(GREATER_THAN);
        var bankAccounts = isLookingForGreater
                ? bankAccountRepository.findBankAccountsByBalanceGreaterThan(query.getBalance())
                : bankAccountRepository.findBankAccountsByBalanceLessThan(query.getBalance());
        return bankAccounts.isEmpty()
                ? new AccountLookupResponse("No bank accounts found")
                : new AccountLookupResponse(SUCCESSFULL_SEARCH_RESPONSE_MESSAGE, bankAccounts);
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.isEmpty()
                ? new AccountLookupResponse("No bank accounts found!")
                : new AccountLookupResponse(SUCCESSFULL_SEARCH_RESPONSE_MESSAGE, bankAccounts);
    }
}
