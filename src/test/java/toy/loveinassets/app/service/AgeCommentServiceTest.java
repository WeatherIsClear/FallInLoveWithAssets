package toy.loveinassets.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.AgeComment;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.BoardRegistrationDto;
import toy.loveinassets.app.dto.AgeCommentRequest;
import toy.loveinassets.app.repository.AgeBoardRepository;
import toy.loveinassets.app.repository.AgeCommentRepository;
import toy.loveinassets.app.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class AgeCommentServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AgeBoardRepository ageBoardRepository;
    @Autowired
    AgeCommentRepository ageCommentRepository;
    @Autowired
    AgeCommentService ageCommentService;

    AgeBoard savedAgeBoard;

    AgeComment parentComment;

    @BeforeEach
    void initData() {
        Member member = memberRepository.findById(1L).get();

        AgeBoard ageBoard = AgeBoard.of(member,
                new BoardRegistrationDto(1L, "title", "content"));

        savedAgeBoard = ageBoardRepository.save(ageBoard);

        parentComment = AgeComment.of(member, ageBoard, "parentComment");
        ageCommentRepository.save(parentComment);
    }

    @Test
    void parentComment() {
        AgeComment parent = ageCommentService.comment(
                new AgeCommentRequest(1L, savedAgeBoard.getId(), 20L, "parent"));

        assertThat(parent.getMember().getId()).isEqualTo(1L);
        assertThat(parent.getAgeBoard().getId()).isEqualTo(savedAgeBoard.getId());
        assertThat(parent.getParent()).isNull();
        assertThat(parent.getChildren().size()).isEqualTo(0);
        assertThat(parent.getContent()).isEqualTo("parent");
    }

    @Test
    void childComment() {
        AgeComment child = null;
        for (int i = 0; i < 10; i++) {
            child = ageCommentService.comment(
                    new AgeCommentRequest(1L, savedAgeBoard.getId(), parentComment.getId(), "child"));
        }

        assertThat(child.getParent().getId()).isEqualTo(parentComment.getId());
        assertThat(parentComment.getChildren().size()).isEqualTo(10);

        AgeComment childChild = null;
        for (int i = 0; i < 3; i++) {
            childChild = ageCommentService.comment(
                    new AgeCommentRequest(2L, savedAgeBoard.getId(), child.getId(), "ch"));
        }

        assertThat(child.getChildren().size()).isEqualTo(3);
        assertThat(childChild.getParent().getId()).isEqualTo(child.getId());
    }

}