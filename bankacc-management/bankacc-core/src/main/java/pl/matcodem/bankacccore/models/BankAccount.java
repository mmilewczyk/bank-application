package pl.matcodem.bankacccore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankAccount {

    @Id
    private Long id;
    private String accountHolderId;
    private LocalDate creationDate;
    private AccountType accountType;
    private BigDecimal balance;
}
