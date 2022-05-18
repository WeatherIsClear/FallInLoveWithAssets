package toy.loveinassets.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.GosuBoard;
import toy.loveinassets.app.repository.querydsl.AgeBoardRepositoryCustom;
import toy.loveinassets.app.repository.querydsl.GosuBoardRepositoryCustom;

public interface GosuBoardRepository extends JpaRepository<GosuBoard, Long>, GosuBoardRepositoryCustom {
}
