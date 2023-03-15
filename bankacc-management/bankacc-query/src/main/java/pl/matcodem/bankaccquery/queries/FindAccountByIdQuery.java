package pl.matcodem.bankaccquery.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByIdQuery implements Query {
    private String accountId;
}
