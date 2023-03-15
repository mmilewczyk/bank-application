package pl.matcodem.bankaccquery.dto;

import pl.matcodem.bankacccore.dto.BaseResponse;
import pl.matcodem.bankacccore.models.BankAccount;

import java.util.List;

import static java.util.Collections.*;

public class AccountLookupResponse extends BaseResponse {

    private List<BankAccount> bankAccounts;

    public AccountLookupResponse(String message) {
        super(message);
    }
    public AccountLookupResponse(String message, List<BankAccount> bankAccounts) {
        super(message);
        this.bankAccounts = bankAccounts;
    }
    public AccountLookupResponse(String message, BankAccount bankAccount) {
        super(message);
        this.bankAccounts = singletonList(bankAccount);
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
