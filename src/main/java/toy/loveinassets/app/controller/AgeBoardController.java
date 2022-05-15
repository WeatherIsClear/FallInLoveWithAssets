package toy.loveinassets.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.dto.AgeBoardListResponse;
import toy.loveinassets.app.service.query.AgeBoardQueryService;

@Controller
@RequiredArgsConstructor
public class AgeBoardController {

    private final AgeBoardQueryService ageBoardQueryService;

    @GetMapping("/age-board/{memberId}")
    public String ageBoardList(@PathVariable Long memberId, @RequestParam int page, Model model) {
        Page<AgeBoardListResponse> ageBoards = ageBoardQueryService.ageBoardList(memberId, page);
        model.addAttribute("ageBoards", ageBoards);
        return "ageboard/ageBoards";
    }
}
