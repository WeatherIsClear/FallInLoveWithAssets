package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.bank.domain.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
}
