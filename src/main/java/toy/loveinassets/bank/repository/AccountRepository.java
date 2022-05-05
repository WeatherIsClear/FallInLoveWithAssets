package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.bank.domain.account.DepositAccount;

public interface AccountRepository extends JpaRepository<DepositAccount, Long> {
}
