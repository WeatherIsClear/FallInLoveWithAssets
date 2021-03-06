package toy.loveinassets.app.service.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.AgeBoardDetailsResponse;
import toy.loveinassets.app.dto.AgeBoardsResponse;
import toy.loveinassets.app.dto.AgeCommentResponse;
import toy.loveinassets.app.repository.AgeBoardRepository;
import toy.loveinassets.app.repository.AgeCommentRepository;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.app.repository.PageSize;

import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Direction.*;
import static toy.loveinassets.app.repository.PageSize.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgeBoardQueryService {

    private final MemberRepository memberRepository;
    private final AgeBoardRepository ageBoardRepository;
    private final AgeCommentRepository ageCommentRepository;

    public Page<AgeBoardsResponse> ageBoards(Long memberId, int page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        AgeGroup ageGroup = AgeGroup.getAgeGroup(member.getMemberYear());

        return ageBoardRepository.ageBoardList(ageGroup, PageRequest.of(page, TEN.getSize(),
                by(DESC, "createdDate")));
    }


    public AgeBoardDetailsResponse ageBoardDetails(Long ageBoardId) {
        AgeBoard ageBoard = ageBoardRepository.findById(ageBoardId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글 입니다."));

        ageBoard.view();

        Page<AgeCommentResponse> commentResponses = getComments(ageBoardId);

        return new AgeBoardDetailsResponse(ageBoard, commentResponses);
    }

    private Page<AgeCommentResponse> getComments(Long ageBoardId) {
        return ageCommentRepository.ageComments(ageBoardId,
                PageRequest.of(0, TEN.getSize(), by(DESC, "createdDate")));
    }
}
