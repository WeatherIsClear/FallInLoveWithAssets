package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.dto.AgeCommentResponse;
import toy.loveinassets.app.dto.GosuCommentResponse;

public interface GosuCommentRepositoryCustom {

    Page<GosuCommentResponse> gosuComments(Long gosuBoardId, Pageable pageable);
}
