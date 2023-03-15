package pl.matcodem.bankaccquery.handlers;

import pl.matcodem.bankaccquery.queries.FindAccountByHolderIdQuery;
import pl.matcodem.bankaccquery.queries.FindAccountByIdQuery;
import pl.matcodem.bankaccquery.queries.FindAccountsWithBalanceQuery;
import pl.matcodem.bankaccquery.queries.FindAllAccountsQuery;
import pl.matcodem.bankaccquery.dto.AccountLookupResponse;

public interface BankAccountQueryHandler {

    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
}
