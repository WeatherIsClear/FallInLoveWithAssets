package toy.loveinassets.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import toy.loveinassets.app.dto.AgeBoardsResponse;
import toy.loveinassets.app.service.query.AgeBoardQueryService;

@Controller
@RequiredArgsConstructor
public class AgeBoardController {

    private final AgeBoardQueryService ageBoardQueryService;

    @GetMapping("/age-board/{memberId}")
    public String ageBoardList(@PathVariable Long memberId, @RequestParam int page, Model model) {
        Page<AgeBoardsResponse> ageBoards = ageBoardQueryService.ageBoards(memberId, page);
        model.addAttribute("ageBoards", ageBoards);
        return "ageboard/ageBoards";
    }
}
