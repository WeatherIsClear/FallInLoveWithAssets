package toy.loveinassets.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.BoardRegistrationDto;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AgeBoardServiceTest {

    @Autowired
    AgeBoardService ageBoardService;
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
}