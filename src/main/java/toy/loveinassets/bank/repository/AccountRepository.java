package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.domain.account.DepositAccount;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(String number);
}
