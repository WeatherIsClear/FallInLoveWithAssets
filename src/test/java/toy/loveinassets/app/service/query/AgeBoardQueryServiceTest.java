package toy.loveinassets.app.service.query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import toy.loveinassets.app.domain.AgeBoard;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgeBoardQueryServiceTest {

    @Autowired
    AgeBoardQueryService ageBoardQueryService;

    @Test
    void ageBoardList() {
        Page<AgeBoard> ageBoards =
                ageBoardQueryService.ageBoardList(1L, PageRequest.of(1, 5));
    }
}