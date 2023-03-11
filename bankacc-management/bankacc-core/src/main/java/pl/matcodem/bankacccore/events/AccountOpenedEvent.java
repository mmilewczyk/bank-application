package pl.matcodem.bankacccore.events;

import lombok.Builder;
import lombok.Data;
import pl.matcodem.bankacccore.models.AccountType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AccountOpenedEvent {

    private String id;
    private String accountHolderId;
    private AccountType accountType;
    private LocalDate creationDate;
    private BigDecimal openingBalance;
}
