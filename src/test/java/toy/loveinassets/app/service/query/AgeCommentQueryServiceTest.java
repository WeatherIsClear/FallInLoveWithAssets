package toy.loveinassets.app.service.query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.AgeComment;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.AgeBoardDetailsResponse;
import toy.loveinassets.app.dto.AgeBoardRegistrationDto;
import toy.loveinassets.app.dto.AgeCommentResponse;
import toy.loveinassets.app.dto.MemberDto;
import toy.loveinassets.app.repository.AgeBoardRepository;
import toy.loveinassets.app.repository.AgeCommentRepository;
import toy.loveinassets.app.repository.MemberRepository;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AgeCommentQueryServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AgeBoardRepository ageBoardRepository;
    @Autowired
    AgeCommentRepository ageCommentRepository;
    @Autowired
    AgeCommentQueryService ageCommentQueryService;

    Member member20;

    AgeBoard ageBoard;

    @BeforeEach
    void initDate() {
        MemberDto memberDto = new MemberDto("20대", "20@20.com",
                of(1998, 1, 1), "rrn");
        member20 = Member.of(memberDto);

        memberRepository.save(member20);

        ageBoard = AgeBoard.of(member20, new AgeBoardRegistrationDto(1L, "detail", "details"));
        ageBoardRepository.save(ageBoard);
    }

    @Test
    void getComments() {
        for (int i = 0; i < 50; i++) {
            AgeComment of = AgeComment.of(member20, ageBoard, "content" + i);
            ageCommentRepository.save(of);
        }

        Page<AgeCommentResponse> page = null;
        for (int i = 0; i < 5; i++) {
            page = ageCommentQueryService.comments(ageBoard.getId(), i);
            assertThat(page.getSize()).isEqualTo(10);
        }

        assertThat(page.getSort().isSorted()).isTrue();
        assertThat(page.getTotalElements()).isEqualTo(50);
        assertThat(page.getTotalPages()).isEqualTo(5);
    }

    @Test
    void nestedComment() {
        AgeComment of = AgeComment.of(member20, ageBoard, "content");
        ageCommentRepository.save(of);

        for (int i = 0; i < 30; i++) {
            AgeComment comment = AgeComment.of(member20, ageBoard, "nested" + i);
            comment.addComment(of);
            ageCommentRepository.save(comment);
        }

        Page<AgeCommentResponse> page = null;
        for (int i = 0; i < 3; i++) {
            page = ageCommentQueryService.nestedComments(of.getId(), i);
            assertThat(page.getSize()).isEqualTo(10);
        }

        assertThat(page.getSort().isSorted()).isTrue();
        assertThat(page.getTotalElements()).isEqualTo(30);
        assertThat(page.getTotalPages()).isEqualTo(3);
    }


}