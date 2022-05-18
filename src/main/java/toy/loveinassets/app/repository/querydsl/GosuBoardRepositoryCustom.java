package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.AgeBoardsResponse;
import toy.loveinassets.app.dto.AgeCommentResponse;
import toy.loveinassets.app.dto.GosuBoardsResponse;
import toy.loveinassets.app.dto.GosuCommentResponse;

public interface GosuBoardRepositoryCustom {

    Page<GosuBoardsResponse> gosuBoardList(Pageable pageable);
}
