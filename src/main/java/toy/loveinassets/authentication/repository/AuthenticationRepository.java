package toy.loveinassets.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.authentication.domain.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
}
