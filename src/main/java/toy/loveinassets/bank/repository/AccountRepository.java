package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.bank.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
