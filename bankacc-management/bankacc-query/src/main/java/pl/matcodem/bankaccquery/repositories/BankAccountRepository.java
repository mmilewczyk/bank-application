package pl.matcodem.bankaccquery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matcodem.bankacccore.models.BankAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    Optional<BankAccount> findBankAccountByAccountHolderId(String accountHolderId);
    List<BankAccount> findBankAccountsByBalanceGreaterThan(BigDecimal balance);
    List<BankAccount> findBankAccountsByBalanceLessThan(BigDecimal balance);
}
