package toy.loveinassets.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.app.domain.AgeComment;
import toy.loveinassets.app.repository.querydsl.AgeCommentRepositoryCustom;

public interface AgeCommentRepository extends JpaRepository<AgeComment, Long>, AgeCommentRepositoryCustom {

}
