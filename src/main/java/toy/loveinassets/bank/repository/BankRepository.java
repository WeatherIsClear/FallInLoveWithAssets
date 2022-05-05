package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.bank.domain.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
