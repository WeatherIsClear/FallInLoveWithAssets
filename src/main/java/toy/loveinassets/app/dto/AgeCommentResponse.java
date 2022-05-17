package toy.loveinassets.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AgeCommentResponse {

    private Long ageCommentId;

    private Long memberId;

    private String memberName;

    private LocalDateTime date;

    private String content;

    private int childComment;


    @QueryProjection
    public AgeCommentResponse(Long ageCommentId, Long memberId, String memberName, LocalDateTime date, String content, int childComment) {
        this.ageCommentId = ageCommentId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.date = date;
        this.content = content;
        this.childComment = childComment;
    }
}
