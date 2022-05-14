package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.loveinassets.bank.domain.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
