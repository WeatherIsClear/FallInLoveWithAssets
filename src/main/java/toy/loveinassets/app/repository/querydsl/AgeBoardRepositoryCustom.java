package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.AgeBoardListResponse;

public interface AgeBoardRepositoryCustom {

    Page<AgeBoardListResponse> ageBoardList(AgeGroup ageGroup, Pageable pageable);
}
