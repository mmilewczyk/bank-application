package pl.matcodem.bankaccquery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matcodem.bankacccore.models.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
