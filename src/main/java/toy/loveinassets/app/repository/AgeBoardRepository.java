package toy.loveinassets.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.repository.querydsl.AgeBoardRepositoryCustom;

public interface AgeBoardRepository extends JpaRepository<AgeBoard, Long>, AgeBoardRepositoryCustom {
}
