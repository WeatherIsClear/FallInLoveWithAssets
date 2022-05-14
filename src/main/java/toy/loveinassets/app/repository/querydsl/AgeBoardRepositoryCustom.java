package toy.loveinassets.app.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.enums.AgeGroup;

import java.util.List;

public interface AgeBoardRepositoryCustom {

    Page<AgeBoard> ageBoardList(AgeGroup ageGroup, Pageable pageable);
}
