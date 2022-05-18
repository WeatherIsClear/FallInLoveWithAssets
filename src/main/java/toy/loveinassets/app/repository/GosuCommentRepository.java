package toy.loveinassets.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.app.domain.AgeComment;
import toy.loveinassets.app.domain.GosuComment;
import toy.loveinassets.app.repository.querydsl.AgeCommentRepositoryCustom;
import toy.loveinassets.app.repository.querydsl.GosuCommentRepositoryCustom;

public interface GosuCommentRepository extends JpaRepository<GosuComment, Long>, GosuCommentRepositoryCustom {

}
