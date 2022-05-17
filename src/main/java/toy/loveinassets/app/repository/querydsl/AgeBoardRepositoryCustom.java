package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.AgeBoardsResponse;

public interface AgeBoardRepositoryCustom {

    Page<AgeBoardsResponse> ageBoardList(AgeGroup ageGroup, Pageable pageable);
}
