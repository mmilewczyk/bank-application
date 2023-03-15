package pl.matcodem.bankaccquery.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.matcodem.bankaccquery.dto.EqualityType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FindAccountsWithBalanceQuery implements Query {
    private EqualityType equalityType;
    private BigDecimal balance;
}
