package pl.matcodem.bankaccquery.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByHolderIdQuery implements Query {
    private String accountHolderId;
}
