package toy.loveinassets.app.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.dto.AgeCommentResponse;
import toy.loveinassets.app.repository.AgeCommentRepository;
import toy.loveinassets.app.repository.PageSize;

import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Direction.*;
import static toy.loveinassets.app.repository.PageSize.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgeCommentQueryService {

    private final AgeCommentRepository ageCommentRepository;

    public Page<AgeCommentResponse> comments(Long ageBoardId, int page) {
        return ageCommentRepository.ageComments(ageBoardId, ofPageable(page));
    }

    public Page<AgeCommentResponse> nestedComments(Long parentId, int page) {
        return ageCommentRepository.nestedComments(parentId, ofPageable(page));
    }

    private PageRequest ofPageable(int page) {
        return PageRequest.of(page, TEN.getSize(), by(DESC, "createdDate"));
    }
}
