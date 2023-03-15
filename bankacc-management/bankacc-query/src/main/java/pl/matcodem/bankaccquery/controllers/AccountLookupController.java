package pl.matcodem.bankaccquery.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.matcodem.bankaccquery.dto.AccountLookupResponse;
import pl.matcodem.bankaccquery.dto.EqualityType;
import pl.matcodem.bankaccquery.queries.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.*;
import static pl.matcodem.bankacccore.configuration.EndpointConstants.BANK_ACCOUNT_ENDPOINT_V1;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = BANK_ACCOUNT_ENDPOINT_V1)
public class AccountLookupController {

    private final QueryGateway queryGateway;

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        try {
            var response = queryToGatewayAndGetResponse(new FindAllAccountsQuery());
            return response == null || response.getBankAccounts() == null
                    ? new ResponseEntity<>(null, NO_CONTENT)
                    : new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all accounts request";
            log.error(e.toString(), e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id) {
        try {
            var response = queryToGatewayAndGetResponse(new FindAccountByIdQuery(id));
            return response == null || response.getBankAccounts() == null
                    ? new ResponseEntity<>(null, NO_CONTENT)
                    : new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by ID request";
            log.error(e.toString(), e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/holder/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable(value = "accountHolderId") String accountHolderId) {
        try {
            var response = queryToGatewayAndGetResponse(new FindAccountByHolderIdQuery(accountHolderId));
            return response == null || response.getBankAccounts() == null
                    ? new ResponseEntity<>(null, NO_CONTENT)
                    : new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by holder ID request";
            log.error(e.toString(), e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable(value = "equalityType") EqualityType equalityType,
                                                                      @PathVariable(value = "balance") BigDecimal balance) {
        try {
            var response = queryToGatewayAndGetResponse(new FindAccountsWithBalanceQuery(equalityType, balance));
            return response == null || response.getBankAccounts() == null
                    ? new ResponseEntity<>(null, NO_CONTENT)
                    : new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get accounts with balance request";
            log.error(e.toString(), e);

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }

    private AccountLookupResponse queryToGatewayAndGetResponse(Query query) {
        return queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
    }
}
