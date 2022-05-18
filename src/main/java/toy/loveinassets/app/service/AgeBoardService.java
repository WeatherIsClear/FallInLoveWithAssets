package toy.loveinassets.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.BoardRegistrationDto;
import toy.loveinassets.app.repository.AgeBoardRepository;
import toy.loveinassets.app.repository.MemberRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AgeBoardService {

    private final MemberRepository memberRepository;
    private final AgeBoardRepository ageBoardRepository;

    public AgeBoard ageBoardRegistration(BoardRegistrationDto request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        AgeBoard ageBoard = AgeBoard.of(member, request);

        return ageBoardRepository.save(ageBoard);
    }
}
