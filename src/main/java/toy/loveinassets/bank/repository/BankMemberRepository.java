package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.bank.domain.BankMember;

public interface BankMemberRepository extends JpaRepository<BankMember, Long> {
    boolean existsBankMemberByNameAndRrn(String name, String rrn);
    BankMember findByNameAndRrn(String name, String rrn);
}
