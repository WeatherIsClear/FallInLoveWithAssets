package toy.loveinassets.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GosuBoardsResponse {

    private Long gosuBoardId;

    private String memberName;

    private String title;

    private String content;

    private LocalDateTime time;

    @QueryProjection

    public GosuBoardsResponse(Long gosuBoardId, String memberName, String title, String content, LocalDateTime time) {
        this.gosuBoardId = gosuBoardId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
        this.time = time;
    }
}
