package toy.loveinassets.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.enums.AgeGroup;

import java.time.LocalDateTime;

@Getter
public class AgeBoardDetailsResponse {

    private Long ageBoardId;

    private Long memberId;

    private String memberName;

    private String title;

    private String content;

    private LocalDateTime date;

    private AgeGroup ageGroup;

    private Page<AgeCommentResponse> comments;

    public AgeBoardDetailsResponse(AgeBoard ageBoard, Page<AgeCommentResponse> comments) {
        this.ageBoardId = ageBoard.getId();
        this.memberId = ageBoard.getMember().getId();
        this.memberName = ageBoard.getMember().getName();
        this.title = ageBoard.getTitle();
        this.content = ageBoard.getContent();
        this.date = ageBoard.getCreatedDate();
        this.ageGroup = ageBoard.getAgeGroup();
        this.comments = comments;
    }
}
