package toy.loveinassets.app.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.AgeBoardListResponse;
import toy.loveinassets.app.repository.AgeBoardRepository;
import toy.loveinassets.app.repository.MemberRepository;

import static org.springframework.data.domain.Sort.Direction.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgeBoardQueryService {

    private final MemberRepository memberRepository;
    private final AgeBoardRepository ageBoardRepository;

    public Page<AgeBoardListResponse> ageBoardList(Long memberId, int page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        AgeGroup ageGroup = AgeGroup.getAgeGroup(member.getMemberYear());

        return ageBoardRepository.ageBoardList(ageGroup, PageRequest.of(page, 10,
                Sort.by(DESC, "createdDate")));
    }
}
