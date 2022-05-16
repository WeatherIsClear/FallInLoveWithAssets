package toy.loveinassets.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgeCommentRequest {

    private Long memberId;

    private Long ageBoardId;

    private Long parentId;

    private String content;
}
