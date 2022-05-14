package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.loveinassets.bank.domain.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Authentication findByNameAndRrn(String name, String rrn);
}
