package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.dto.AgeCommentResponse;

public interface AgeCommentRepositoryCustom {

    Page<AgeCommentResponse> ageComments(Long ageBoardId, Pageable pageable);

    Page<AgeCommentResponse> nestedComments(Long parentId, Pageable pageable);
}
