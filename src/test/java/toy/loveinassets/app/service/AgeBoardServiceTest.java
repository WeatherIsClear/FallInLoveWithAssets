package toy.loveinassets.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.BoardRegistrationDto;
import toy.loveinassets.app.dto.BoardUpdateRequest;
import toy.loveinassets.app.repository.AgeBoardRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class AgeBoardServiceTest {

    @Autowired
    AgeBoardService ageBoardService;
    @Autowired
    EntityManager em;
    @Autowired
    AgeBoardRepository ageBoardRepository;
    BoardRegistrationDto request;

    @BeforeEach
    void initData() {
        request = new BoardRegistrationDto(1L, "title", "content");
    }

    @Test
    void ageBoardRegistration() {

        AgeBoard ageBoard = ageBoardService.ageBoardRegistration(request);

        assertThat(ageBoard.getTitle()).isEqualTo("title");
        assertThat(ageBoard.getContent()).isEqualTo("content");
        assertThat(ageBoard.getAgeGroup()).isEqualTo(AgeGroup.TWENTIES);
    }

    @Test
    void ageBoardUpdate() {
        AgeBoard ageBoard = ageBoardService.ageBoardRegistration(request);

        BoardUpdateRequest update = new BoardUpdateRequest(
                1L,
                ageBoard.getId(),
                "update",
                "updateTest");

        assertThat(ageBoard.getTitle()).isEqualTo("title");
        assertThat(ageBoard.getContent()).isEqualTo("content");
        ageBoardService.ageBoardUpdate(update);

        em.flush();
        em.clear();

        AgeBoard findAgeBoard = em.find(AgeBoard.class, ageBoard.getId());
        assertThat(findAgeBoard.getTitle()).isEqualTo("update");
        assertThat(findAgeBoard.getContent()).isEqualTo("updateTest");
    }

    @Test
    @DisplayName("게시글 작성자가 아니면 수정 불가")
    void notBoardWriterUpdate() {
        AgeBoard ageBoard = ageBoardService.ageBoardRegistration(request);

        BoardUpdateRequest update = new BoardUpdateRequest(
                2L,
                ageBoard.getId(),
                "update",
                "updateTest");

        assertThatThrownBy(() -> ageBoardService.ageBoardUpdate(update))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deleteTest() {
        AgeBoard ageBoard = ageBoardService.ageBoardRegistration(request);

        ageBoardService.ageBoardDelete(1L, ageBoard.getId());

        em.flush();
        em.clear();

        assertThatThrownBy(() -> ageBoardRepository.findById(ageBoard.getId())
                .orElseThrow(() -> new IllegalStateException("삭제된 게시글 입니다.")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("게시글 작성자가 아니면 삭제 불가")
    void notBoardWriterDelete() {
        AgeBoard ageBoard = ageBoardService.ageBoardRegistration(request);

        assertThatThrownBy(() -> ageBoardService.ageBoardDelete(2L, ageBoard.getId()))
                .isInstanceOf(IllegalStateException.class);
    }
}