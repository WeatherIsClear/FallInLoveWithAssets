package toy.loveinassets.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.app.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}
