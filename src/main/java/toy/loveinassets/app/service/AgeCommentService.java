package toy.loveinassets.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.AgeComment;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.AgeCommentRequest;
import toy.loveinassets.app.repository.AgeBoardRepository;
import toy.loveinassets.app.repository.AgeCommentRepository;
import toy.loveinassets.app.repository.MemberRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AgeCommentService {

    private final MemberRepository memberRepository;
    private final AgeBoardRepository ageBoardRepository;
    private final AgeCommentRepository ageCommentRepository;

    public AgeComment comment(AgeCommentRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        AgeBoard ageBoard = ageBoardRepository.findById(request.getAgeBoardId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글입니다."));

        AgeComment ageComment = AgeComment.of(member, ageBoard, request.getContent());

        commentParent(ageComment, request.getParentId());

        return ageCommentRepository.save(ageComment);
    }

    private void commentParent(AgeComment ageComment, Long parentId) {
        ageCommentRepository.findById(parentId).ifPresent(ageComment::addComment);
    }
}
