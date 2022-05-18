package toy.loveinassets.app.service.query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.AgeComment;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.*;
import toy.loveinassets.app.repository.AgeBoardRepository;
import toy.loveinassets.app.repository.AgeCommentRepository;
import toy.loveinassets.app.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AgeBoardQueryServiceTest {

    @Autowired
    AgeBoardQueryService ageBoardQueryService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AgeBoardRepository ageBoardRepository;
    @Autowired
    AgeCommentRepository ageCommentRepository;
    @Autowired
    EntityManager em;

    Member member20;
    Member member30;
    Member member40;
    Member member50;

    AgeBoard detail;

    @BeforeEach
    void initDate() {
        MemberDto memberDto = new MemberDto("20대", "20@20.com",
                of(1998, 1, 1), "rrn");
        MemberDto memberDtoA = new MemberDto("30대", "30@30.com",
                of(1990, 1, 1), "rrn");
        MemberDto memberDtoB = new MemberDto("40대", "40@40.com",
                of(1980, 1, 1), "rrn");
        MemberDto memberDtoC = new MemberDto("50대", "50@50.com",
                of(1970, 1, 1), "rrn");

        member20 = Member.of(memberDto);
        member30 = Member.of(memberDtoA);
        member40 = Member.of(memberDtoB);
        member50 = Member.of(memberDtoC);

        memberRepository.save(member20);
        memberRepository.save(member30);
        memberRepository.save(member40);
        memberRepository.save(member50);

        for (int i = 0; i < 10; i ++) {
            ageBoardRepository.save(AgeBoard.of(member20,
                    new BoardRegistrationDto(member20.getId(), "20대", "20대")));
        }
        for (int i = 0; i < 8; i ++) {
            ageBoardRepository.save(AgeBoard.of(member30,
                    new BoardRegistrationDto(member30.getId(), "30대", "30대")));
        }
        for (int i = 0; i < 6; i ++) {
            ageBoardRepository.save(AgeBoard.of(member40,
                    new BoardRegistrationDto(member40.getId(), "40대", "40대")));
        }
        for (int i = 0; i < 4; i ++) {
            ageBoardRepository.save(AgeBoard.of(member50,
                    new BoardRegistrationDto(member50.getId(), "50대", "50대")));
        }

        detail = AgeBoard.of(member30, new BoardRegistrationDto(1L, "detail", "details"));
        ageBoardRepository.save(detail);
    }

    @Test
    @DisplayName("페이징 테스트")
    void pagingTest() {

        Page<AgeBoardsResponse> ageBoards = null;


        for (int i = 0; i < 5; i++) {
            long start = System.currentTimeMillis();
            ageBoards = ageBoardQueryService.ageBoards(1L, i);
            long end = System.currentTimeMillis();
            System.out.println((end - start));
            assertThat(ageBoards.getSize()).isEqualTo(10);
        }

        assertThat(ageBoards.getSort().isSorted()).isTrue();
        assertThat(ageBoards.getTotalElements()).isEqualTo(50);
        assertThat(ageBoards.getTotalPages()).isEqualTo(5);
    }

    @Test
    @DisplayName("날짜별 내림차순 정렬 테스트")
    void sortTest() {
        LocalDateTime date = LocalDateTime.of(9999, 12, 31, 12, 59);

        for (int i = 0; i < 5; i++) {
            Page<AgeBoardsResponse> ageBoards = ageBoardQueryService.ageBoards(1L, i);
            for (int j = 0; j < 10; j++) {
                date = sortChecked(date, ageBoards, j);
            }
        }
    }

    private LocalDateTime sortChecked(LocalDateTime date, Page<AgeBoardsResponse> ageBoards, int j) {
        if (date.isBefore(ageBoards.getContent().get(j).getTime())) {
            throw new RuntimeException("정렬 실패");
        } else {
            return ageBoards.getContent().get(j).getTime();
        }
    }

    @Test
    @DisplayName("연령대별 테스트")
    void ageGroupTest() {
        Page<AgeBoardsResponse> twenties = ageBoardQueryService.ageBoards(member20.getId(), 0);
        Page<AgeBoardsResponse> thirties = ageBoardQueryService.ageBoards(member30.getId(), 0);
        Page<AgeBoardsResponse> forties = ageBoardQueryService.ageBoards(member40.getId(), 0);
        Page<AgeBoardsResponse> fifties = ageBoardQueryService.ageBoards(member50.getId(), 0);

        assertThat(twenties.getTotalElements()).isEqualTo(10L);
        assertThat(thirties.getTotalElements()).isEqualTo(9L);
        assertThat(forties.getTotalElements()).isEqualTo(6L);
        assertThat(fifties.getTotalElements()).isEqualTo(4L);
    }

    @Test
    @DisplayName("연령게시판 상세보기")
    void ageBoardDetails() {
        for (int i = 0; i < 7; i++) {
            ageCommentRepository.save(AgeComment.of(member30, detail, "content"));
        }

        AgeBoardDetailsResponse ageBoardDetailsResponse = ageBoardQueryService.ageBoardDetails(detail.getId());

        assertThat(ageBoardDetailsResponse.getAgeBoardId()).isEqualTo(detail.getId());
        assertThat(ageBoardDetailsResponse.getMemberId()).isEqualTo(member30.getId());
        assertThat(ageBoardDetailsResponse.getComments().getTotalElements()).isEqualTo(7L);
    }

    @Test
    @DisplayName("상세보기 댓글 없음")
    void ageBoardDetailsEmptyComment() {

        AgeBoardDetailsResponse ageBoardDetailsResponse = ageBoardQueryService.ageBoardDetails(detail.getId());

        assertThat(ageBoardDetailsResponse.getComments().isEmpty()).isTrue();
    }

    @Test
    @DisplayName("답글 개수 테스트")
    void coCommentCountTest() {
        AgeComment parent = AgeComment.of(member30, detail, "content");
        ageCommentRepository.save(parent);
        for (int i = 0; i < 30; i++) {
            AgeComment of = AgeComment.of(member30, detail, "content");
            of.addComment(parent);
            ageCommentRepository.save(of);
        }
        AgeBoardDetailsResponse ageBoardDetailsResponse = ageBoardQueryService.ageBoardDetails(detail.getId());

        assertThat(ageBoardDetailsResponse.getComments().getTotalElements()).isEqualTo(1L);
        assertThat(ageBoardDetailsResponse.getComments().getContent().get(0).getChildComment()).isEqualTo(30);
    }
}