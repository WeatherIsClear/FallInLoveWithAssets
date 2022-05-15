package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.loveinassets.bank.domain.History;
import toy.loveinassets.bank.domain.account.Account;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findDepositByToAccount(Account toAccount);
    List<History> findWithdrawalByFromAccount(Account fromAccount);
}
