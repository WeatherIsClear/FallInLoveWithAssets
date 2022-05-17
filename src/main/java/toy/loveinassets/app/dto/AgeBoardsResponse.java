package toy.loveinassets.app.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AgeBoardsResponse {

    private Long ageBoardId;

    private String memberName;

    private String title;

    private String content;

    private LocalDateTime time;

    @QueryProjection

    public AgeBoardsResponse(Long ageBoardId, String memberName, String title, String content, LocalDateTime time) {
        this.ageBoardId = ageBoardId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
        this.time = time;
    }
}
