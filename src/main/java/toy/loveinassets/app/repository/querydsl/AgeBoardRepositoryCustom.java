package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.AgeBoardDetailsResponse;
import toy.loveinassets.app.dto.AgeBoardListResponse;

public interface AgeBoardRepositoryCustom {

    Page<AgeBoard> ageBoardList(AgeGroup ageGroup, Pageable pageable);
}
