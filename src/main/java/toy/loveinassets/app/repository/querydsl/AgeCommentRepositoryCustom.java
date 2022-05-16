package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.domain.AgeComment;

public interface AgeCommentRepositoryCustom {

    Page<AgeComment> ageComments(Long ageBoardId, Pageable pageable);
}
