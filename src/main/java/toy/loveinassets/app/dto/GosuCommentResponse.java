package toy.loveinassets.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GosuCommentResponse {

    private Long gosuCommentId;

    private Long memberId;

    private String memberName;

    private LocalDateTime date;

    private String content;

    private int childComment;


    @QueryProjection
    public GosuCommentResponse(Long gosuCommentId, Long memberId, String memberName, LocalDateTime date, String content, int childComment) {
        this.gosuCommentId = gosuCommentId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.date = date;
        this.content = content;
        this.childComment = childComment;
    }
}
